package com.example.productService.RepoTest;

import com.example.productService.models.Categories;
import com.example.productService.models.Product;
import com.example.productService.repository.CategoryRepository;
import com.example.productService.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
public class ProductRepoTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepo;
    @Test
    @Transactional
    public void testFindbyProductId(){

    Product product=this.productRepository.findProductById(1L);
    System.out.println("Debug");

    }
    @Test
//    @Transactional
//    @Rollback(value = false)
    @Commit
    void saveProductsAndCategory() {
        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories = categoryRepo.save(categories);

        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        product.setCategory(categories);
        productRepository.save(product);
//
//        Categories categories1 = categoryRepo.findCategoriesById(1L);
////      // List<Product> productList = categories1.getProductList();
//        List<Product> products1=productRepository.findAll();
//        System.out.println("Debug");

    }
}
