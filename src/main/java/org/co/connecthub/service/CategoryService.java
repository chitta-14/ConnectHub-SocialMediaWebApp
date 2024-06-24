package org.co.connecthub.service;

import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    //get
    CategoryDto findById(Integer categoryId);
    //get all
    List<CategoryDto> findAll();
    //delete
    ApiResponse deleteCategory(Integer categoryId);
}
