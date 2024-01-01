package com.example.productService.service;

import com.example.productService.models.Categories;
import com.example.productService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfCategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Categories getSingleCategories(Long categoryId) {
        return this.categoryRepository.findCategoriesById(categoryId);
    }

    @Override
    public Categories saveCategory(Categories categories) {
        return this.categoryRepository.save(categories);
    }

    @Override
    public Categories deleteCatergory(Long categoryId) {
        try{
        this.categoryRepository.deleteById(categoryId);
        }catch (Exception e){
            throw e;
        }
        return null;

    }

    @Override
    public List<Categories> getAllCategories() {
        return this.categoryRepository.findAll();
    }
}
