package com.sky.service;

import com.sky.dto.DishDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;



public interface DishService {

    void saveWithFlavor(@RequestBody DishDTO dishDto);
}
