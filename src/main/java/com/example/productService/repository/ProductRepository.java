package com.example.productService.repository;

import com.example.productService.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

   Product findProductById(Long productId);

   List<Product> findAll();

   Product deleteProductById(Long productId);

   Page<Product> findByTitle(String query, Pageable pageable);

}
