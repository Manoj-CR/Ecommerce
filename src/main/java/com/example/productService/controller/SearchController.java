package com.example.productService.controller;

import com.example.productService.dto.ProductDto;
import com.example.productService.dto.SearchRequestDTO;
import com.example.productService.models.Product;
import com.example.productService.service.SearchService;
import com.example.productService.utility.Product_to_ProductDto;
import jakarta.servlet.http.PushBuilder;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public ResponseEntity<Page<Product>>searchProducts(@RequestBody SearchRequestDTO searchRequestDTO){
        try {
            Page<Product> productList = searchService.searchProducts(searchRequestDTO.getQuery(),
                    searchRequestDTO.getPageNumber(), searchRequestDTO.getSizeofPage(),searchRequestDTO.getSortParam());

      //      Page<ProductDto> productDtoList = Product_to_ProductDto.getProductDtoListFromProductList(productList);

            return new ResponseEntity<>(productList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

}
