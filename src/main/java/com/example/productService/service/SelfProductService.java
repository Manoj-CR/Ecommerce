package com.example.productService.service;

import com.example.productService.models.Product;
import com.example.productService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class SelfProductService implements IProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {

        return this.productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) {

        Product product=this.productRepository.findProductById(productId);
        return product;
    }

    @Override
    public Product addNewProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product patchProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product deleteAProduct(Long productId) {

        return this.productRepository.deleteProductById(productId);
    }
}
