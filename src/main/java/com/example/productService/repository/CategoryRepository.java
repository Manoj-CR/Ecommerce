package com.example.productService.repository;

import com.example.productService.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    Categories findCategoriesById(Long id);
    
    Categories save(Categories categories);

    Categories deleteCategoryById(Long categoryId);


    @Query(value = "SELECT c.name FROM Categories c WHERE c.id = :id")
    String findCategoryNameById(@Param("id") long id);

    @Query(value = "SELECT c.name FROM Categories c WHERE c.id = ?1")
    String findCategoryNameByIdV1(long id);
}
