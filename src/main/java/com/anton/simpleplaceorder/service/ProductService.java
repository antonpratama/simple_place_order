package com.anton.simpleplaceorder.service;

import com.anton.simpleplaceorder.entity.Product;
import com.anton.simpleplaceorder.model.CreateProductRequest;
import com.anton.simpleplaceorder.model.CustomerResponse;
import com.anton.simpleplaceorder.model.ProductResponse;
import com.anton.simpleplaceorder.model.UpdateProductRequest;
import com.anton.simpleplaceorder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public ProductResponse create(CreateProductRequest request){

        validationService.validate(request);

        Product product = new Product();
        product.setName(request.getName());
        product.setType(request.getType());
        product.setPrice(request.getPrice());

        productRepository.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .type(product.getType())
                .price(product.getPrice())
                .build();
    }

    public ProductResponse get(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .type(product.getType())
                .price(product.getPrice())
                .build();
    }

    @Transactional
    public ProductResponse update(UpdateProductRequest request){
        validationService.validate(request);

        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if (Objects.nonNull(request.getName())){
            product.setName(request.getName());
        }

        if (Objects.nonNull(request.getType())){
            product.setType(request.getType());
        }

        product.setPrice(request.getPrice());

        productRepository.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .type(product.getType())
                .price(product.getPrice())
                .build();
    }

    @Transactional
    public void delete(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        productRepository.delete(product);
    }
}
