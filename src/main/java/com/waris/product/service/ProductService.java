package com.waris.product.service;

import com.waris.product.dto.ProductRequest;
import com.waris.product.dto.ProductResponse;
import com.waris.product.model.Product;
import com.waris.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.name());
                product.setDescription(productRequest.description());
                product.setPrice(productRequest.price());

                productRepository.save(product);

                return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
               // logger.info("Product Created Successfully");

    }

    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponse(product.getId(),product.getName(),
                        product.getDescription(),product.getPrice())).toList();

    }
}
