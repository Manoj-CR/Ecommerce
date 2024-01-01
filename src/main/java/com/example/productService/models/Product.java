package com.example.productService.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String title;
    private double price;
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Categories category;
    private String imageUrl;
    @Column(columnDefinition = "boolean default true")
    private Boolean productStatus=Boolean.TRUE;
}
