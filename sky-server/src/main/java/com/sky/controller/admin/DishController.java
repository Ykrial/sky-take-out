package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public Result save(@RequestBody DishDTO dishDto) {
        log.info("新增菜品{}", dishDto);
        dishService.saveWithFlavor(dishDto);
        return Result.success();
    }

    /**
     * 菜品的分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {

        log.info("菜品分页查询{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishVO> getByDishId(@PathVariable Long id) {

        DishVO dishVo = dishService.getByDishId(id);
        return Result.success(dishVo);
    }

    /**
     * 修改菜品
     * @param dishDto
     * @return
     */
    @PutMapping
    public Result update(@RequestBody DishDTO dishDto) {

        log.info("修改菜品{}", dishDto);
        dishService.update(dishDto);

        return Result.success();
    }

    /**
     * 修改菜品状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        dishService.startOrStop(status,id);

        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids) {

        log.info("删除菜品，{}",ids);

        dishService.deleteBatch(ids);
        return Result.success();
    }
}
