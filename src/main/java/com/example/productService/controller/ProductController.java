package com.example.productService.controller;

import com.example.productService.dto.ProductDto;
import com.example.productService.models.Product;
import com.example.productService.service.IProductService;
import com.example.productService.utility.ProductDto_to_Product;
import com.example.productService.utility.Product_to_ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {


    IProductService productService;

    public ProductController(IProductService productService){

        this.productService=productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            header.add("auth-token", "hey access");
            List<Product> productList=this.productService.getAllProduct();
            List<ProductDto> productDtoList=Product_to_ProductDto.getProductDtoListFromProductList(productList);
            return new ResponseEntity<>(productDtoList,header, HttpStatus.OK);
        }catch (Exception e){
            ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>
                    (HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 error code
        }

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProducts(@PathVariable("productId")Long productId){
        try {
//            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
//            header.add("Content-Type", "application/json");
//            header.add("Accept", "application/json");
//            header.add("auth-token", "hey Acess");
            Product product=this.productService.getSingleProduct(productId);
             ProductDto productDto=Product_to_ProductDto.getProductDtoFromProduct(product);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }catch (Exception e){
           // ResponseEntity<Product> responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw e;
        }
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            header.add("auth-token", "hey Acess");
            Product product= ProductDto_to_Product.getProductFromProductDto(productDto);
            return new ResponseEntity<>(this.productService.addNewProduct(product), header, HttpStatus.OK);
        }catch (Exception e){
            ResponseEntity<Product> responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{productId}")
    public String patchProduct(@PathVariable("productId") Long productId, Product product) {
        return "Patching product";
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId) {
       try {
           MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
           header.add("Content-Type", "application/json");
           header.add("Accept", "application/json");
           header.add("auth-token", "hey Acess");
           Product product=this.productService.deleteAProduct(productId);
           return new ResponseEntity<>(product, header, HttpStatus.OK);
       }catch (Exception e){
           ResponseEntity<Product> responseEntity=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }


}
