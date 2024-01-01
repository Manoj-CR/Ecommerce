package com.example.productService.utility;

import com.example.productService.dto.FakeProductDto;
import com.example.productService.dto.ProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Product_to_ProductDto {


    public static List<ProductDto> getProductDtoListFromProductList(List<Product> productList){

        List<ProductDto> productDtoList=new ArrayList<>();
        for(Product product:productList){

            productDtoList.add(getProductDtoFromProduct(product));
        }

        return productDtoList;
    }

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

    public static FakeProductDto getFakeProductDtoFromProduct(Product product){
        FakeProductDto productDto=new FakeProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        //  productDto.setRating(product.getRating());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImageUrl());
        productDto.setCategory(product.getCategory().getDescription());
        return productDto;
    }

}
