package com.example.productService.service;

import com.example.productService.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IProductService {
    List<Product> getAllProduct();

    Product getSingleProduct(Long productId);

    Product addNewProduct(Product product);

    Product updateProduct(Long productId,Product product);

    Product patchProduct(Long productId,Product product);

    Product deleteAProduct(Long productId);

}
