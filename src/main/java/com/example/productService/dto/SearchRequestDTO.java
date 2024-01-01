package com.example.productService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {

    private String query;
    private int pageNumber;
    private int sizeofPage;
    private List<SortParam> sortParam;
}
