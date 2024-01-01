package com.example.productService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating extends BaseModel{
    private double rate;
    private long count;
}
