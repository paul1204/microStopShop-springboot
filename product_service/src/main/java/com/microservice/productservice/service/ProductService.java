package com.microservice.productservice.service;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.ProductRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {}", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> p = productRepository.findAll();
       return p.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product p){
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .description((p.getDescription()))
                .price(p.getPrice())
                .build();
    }
    public void delete(String id){
        productRepository.deleteById(id);
    }
}
