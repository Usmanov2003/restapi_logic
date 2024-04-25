package com.example.rest_apis.services;

import com.example.rest_apis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //read/get
    CategoryDto getCategory(Integer categoryId);

    //getAll
    List<CategoryDto> getAllCategory();
}
