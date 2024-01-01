package com.example.productService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date createdAt=new Date();
    private Date lastModifiedAt;
    private String createdBy;
    private String lastModifiedBy;
}
