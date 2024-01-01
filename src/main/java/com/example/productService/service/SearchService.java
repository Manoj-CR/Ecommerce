package com.example.productService.service;

import com.example.productService.dto.SortParam;
import com.example.productService.models.Product;
import com.example.productService.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> searchProducts(String query, int pageNumber, int sizeofPage, List<SortParam> sortParam) {

        Sort sort = null;
        if(sortParam.get(0).getSortType().equals("ASC")){
            sort=Sort.by(sortParam.get(0).getParameter());
        }else{
            sort= Sort.by(sortParam.get(0).getParameter()).descending();
        }

        for(int i=1;i< sortParam.size();i++){
            if(sortParam.get(i).getSortType().equals("ASC")){
                sort=sort.and(Sort.by(sortParam.get(i).getParameter()));
            }else{
                sort=sort.and(Sort.by(sortParam.get(i).getParameter()).descending());
            }
        }

        return productRepository.findByTitle(query, PageRequest.of(pageNumber,sizeofPage,sort));

    }
}
