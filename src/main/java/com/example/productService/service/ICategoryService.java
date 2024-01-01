package com.example.productService.service;

import com.example.productService.models.Categories;

import java.util.List;

public interface ICategoryService {
    Categories getSingleCategories(Long categoryId);

    Categories saveCategory(Categories categories);

    Categories deleteCatergory(Long categoryId);

    List<Categories> getAllCategories();
}
