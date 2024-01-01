package com.example.productService.utility;

import com.example.productService.dto.FakeProductDto;
import com.example.productService.dto.ProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;

public  class ProductDto_to_Product {


    public static Product getFakeProductFromProductDto(FakeProductDto productDto){
        Product product=new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        // product.setRating(productDto.getRating());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Categories categories=new Categories();
        categories.setDescription(productDto.getCategory());
        product.setCategory(categories);
        return product;
    }

    public static Product getProductFromProductDto(ProductDto productDto){
        Product product=new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        // product.setRating(productDto.getRating());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        Categories categories=new Categories();
        categories.setDescription(productDto.getCategory().getDescription());
        product.setCategory(categories);
        return product;
    }


}
