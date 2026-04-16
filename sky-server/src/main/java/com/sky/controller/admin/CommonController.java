package com.sky.controller.admin;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
@CrossOrigin // 允许跨域请求
public class CommonController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file.getOriginalFilename());

        try {
            // 1. 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 2. 截取后缀名 (例如: .jpg)
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 3. 构造新的文件名 (UUID + 后缀) 防止重复
            String objectName = UUID.randomUUID().toString() + extension;

            // 4. 定义存储路径 (保存到项目根目录下的 images 文件夹)
            String filePath = System.getProperty("user.dir") + "/sky-take-out/images/";
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 5. 将文件保存到本地
            file.transferTo(new File(filePath + objectName));

            // 6. 返回文件的访问路径 (对应 WebMvcConfiguration 中配置的资源映射)
            String url = "http://localhost:8080/images/" + objectName;
            Result<String> result = Result.success(url);
            result.setMsg("文件上传成功");
            return result;

        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
        }

        return Result.error("文件上传失败");
    }
}
