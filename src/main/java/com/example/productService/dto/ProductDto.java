package com.example.productService.dto;

import com.example.productService.models.BaseModel;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class ProductDto extends BaseModel {

    private String title;
    private double price;
    private String description;
    private Categories category;
    private String imageUrl;

    public static ProductDto getProductDtoFromProduct(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        Categories categories= product.getCategory();
        categories.setProductList(new ArrayList<>());
        productDto.setCategory(categories);
        return productDto;
    }
}
