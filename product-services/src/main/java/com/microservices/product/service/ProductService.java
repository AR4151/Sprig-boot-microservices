package com.microservices.product.service;

import com.microservices.product.dto.ProductRequest;
import com.microservices.product.dto.ProductResponse;
import com.microservices.product.model.Product;
import com.microservices.product.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .skuCode(productRequest.skuCode())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product is created successfully!");
        return new ProductResponse(product.getId(),product.getName()
                ,product.getDescription(),product.getPrice());
    }


    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(),product.getName()
                ,product.getDescription(),product.getPrice())).toList();
    }
}
