package com.example.productService.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Categories extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private List<Product> productList;
    @Column(columnDefinition = "boolean default true")
    private Boolean categoryStatus = Boolean.TRUE;

}
