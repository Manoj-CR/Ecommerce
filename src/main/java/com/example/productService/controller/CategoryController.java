package com.example.productService.controller;

import com.example.productService.models.Categories;
import com.example.productService.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Categories>> getAllCategory(){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            header.add("auth-token", "hey Acess");
            return new ResponseEntity<>(this.categoryService.getAllCategories(), header, HttpStatus.OK);
        }catch (Exception e){
             ResponseEntity<List<Categories>> responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            throw e;
        }
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<Categories> getSingleCategory(@PathVariable("categoryId")Long categoryId){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            header.add("auth-token", "hey Acess");
            return new ResponseEntity<>(this.categoryService.getSingleCategories(categoryId), header, HttpStatus.OK);
        }catch (Exception e){
            // ResponseEntity<Product> responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw e;
        }
    }

    @PostMapping("")
    public ResponseEntity<Categories> saveCategory(@RequestBody Categories categories){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            header.add("auth-token", "hey Acess");
            return new ResponseEntity<>(this.categoryService.saveCategory(categories), header, HttpStatus.OK);
        }catch (Exception e){
             ResponseEntity<Categories> responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            throw e;
        }
    }
@Transactional
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Categories> deleteCategory(@PathVariable("categoryId") Long categoryId){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            header.add("auth-token", "hey Acess");
            return new ResponseEntity<>(this.categoryService.deleteCatergory(categoryId), header, HttpStatus.OK);
        }catch (Exception e){
//            ResponseEntity<Categories> responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw e;
        }
    }

}
