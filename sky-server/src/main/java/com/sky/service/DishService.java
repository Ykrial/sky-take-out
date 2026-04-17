package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 新增菜品和口味
     * @param dishDto
     */
    void saveWithFlavor(DishDTO dishDto);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 修改菜品
     * @param dishDto
     */
    void update(DishDTO dishDto);

    /**
     * 根据id查询菜品及其关联的口味信息
     * @param id
     * @return
     */
    DishVO getByDishId(Long id);

    /**
     * 启用或禁用菜品
     */
    void startOrStop(Integer status, Long id);

    void deleteBatch(List<Long> ids);
}
