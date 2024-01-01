package com.example.productService.service;

import com.example.productService.dto.FakeProductDto;
import com.example.productService.models.Categories;
import com.example.productService.models.Product;
import com.example.productService.utility.ProductDto_to_Product;
import com.example.productService.utility.Product_to_ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


//@Primary
@Service
public class FakeStoreProductService implements IProductService{

    RestTemplateBuilder restTemplateBuilder;

    private RedisTemplate<String,String> redisTemplate;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder,RedisTemplate<String,String> redisTemplate) {

        this.restTemplateBuilder = restTemplateBuilder;
        this.redisTemplate=redisTemplate;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    @Override
    public List<Product> getAllProduct() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeProductDto[]> productDtoList=restTemplate
                .getForEntity("https://fakestoreapi.com/products", FakeProductDto[].class);
        List<Product> productList=new ArrayList<>();

        for(FakeProductDto productDto: productDtoList.getBody()){
            Product product=new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
           // product.setRating(productDto.getRating());
            product.setPrice(productDto.getPrice());
            product.setImageUrl(productDto.getImage());
            Categories categories=new Categories();
            categories.setDescription(productDto.getCategory());
            product.setCategory(categories);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        Product redisCache= (Product) redisTemplate.opsForHash().get("PRODUCT",productId);
        if(redisCache==null) {

            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeProductDto> responseEntity = restTemplate.
                    getForEntity("https://fakestoreapi.com/products/{id}", FakeProductDto.class, productId);
            FakeProductDto productDto = responseEntity.getBody();
            redisCache.setId(productDto.getId());
            redisCache.setTitle(productDto.getTitle());
            redisCache.setDescription(productDto.getDescription());
            // product.setRating(productDto.getRating());
            redisCache.setPrice(productDto.getPrice());
            redisCache.setImageUrl(productDto.getImage());
            Categories categories = new Categories();
            categories.setDescription(productDto.getCategory());
            redisCache.setCategory(categories);
            redisTemplate.opsForHash().put("PRODUCT",productId,redisCache);
        }
        return redisCache;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeProductDto productDto= Product_to_ProductDto.getFakeProductDtoFromProduct(product);
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeProductDto> productDtoResponseEntity=restTemplate.
                postForEntity("https://fakestoreapi.com/products",productDto, FakeProductDto.class);
        Product productFromProductDto = ProductDto_to_Product.getFakeProductFromProductDto(productDtoResponseEntity.getBody());
       // System.out.println(productFromProductDto.getId());
        return productFromProductDto;
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
        RestTemplate restTemplate=restTemplateBuilder.build();
        Product product=getSingleProduct(productId);
        restTemplate.delete("https://fakestoreapi.com/products/{id}",productId, FakeProductDto.class);
        return product;
    }


}
