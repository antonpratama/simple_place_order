package com.anton.simpleplaceorder.controller;

import com.anton.simpleplaceorder.model.CreateProductRequest;
import com.anton.simpleplaceorder.model.ProductResponse;
import com.anton.simpleplaceorder.model.UpdateProductRequest;
import com.anton.simpleplaceorder.model.WebResponse;
import com.anton.simpleplaceorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(
            path = "/api/product/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> create(@RequestBody CreateProductRequest request){
        ProductResponse productResponse = productService.create(request);

        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @GetMapping(
            path = "/api/product/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> get(@PathVariable("productId") Long productId){
        ProductResponse productResponse = productService.get(productId);

        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @PutMapping(
            path = "/api/product/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> update(@RequestBody UpdateProductRequest request,
                                               @PathVariable("productId") Long productId){
        request.setId(productId);

        ProductResponse productResponse = productService.update(request);

        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @DeleteMapping(
            path = "/api/product/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("productId") Long productId){
        productService.delete(productId);

        return WebResponse.<String>builder().data("OK").build();
    }
}
