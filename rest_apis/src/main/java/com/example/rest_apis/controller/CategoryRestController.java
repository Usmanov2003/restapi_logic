package com.example.rest_apis.controller;


import com.example.rest_apis.payloads.ApiResponse;
import com.example.rest_apis.payloads.CategoryDto;
import com.example.rest_apis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        System.out.println("Inside Create Cat");
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("catId") Integer catId){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);

        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId){
        this.categoryService.deleteCategory(catId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully!", true), HttpStatus.OK);
    }


    //getSingle
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId){
        CategoryDto category = this.categoryService.getCategory(catId);

        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }


    //getAll
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categories = this.categoryService.getAllCategory();

        return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
    }
}
