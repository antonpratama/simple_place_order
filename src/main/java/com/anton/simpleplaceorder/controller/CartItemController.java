package com.anton.simpleplaceorder.controller;

import com.anton.simpleplaceorder.model.*;
import com.anton.simpleplaceorder.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping(
            path = "/api/cart-item/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CartItemResponse> create(@RequestBody CreateCartItemRequest request){
        CartItemResponse cartItemResponse = cartItemService.create(request);

        return WebResponse.<CartItemResponse>builder().data(cartItemResponse).build();
    }

    @DeleteMapping(
            path = "/api/cart-item/{cartItemId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("cartItemId") UUID cartItemId){
        cartItemService.delete(cartItemId);

        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/cart-item/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ListCartItemResponse>> getList(@PathVariable("customerId") Long customerId){
        List<ListCartItemResponse> listCartItemResponses = cartItemService.list(customerId);

        return WebResponse.<List<ListCartItemResponse>>builder().data(listCartItemResponses).build();
    }

    @GetMapping(
            path = "/api/cart-item/page",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ListCartItemResponse>> getListPaging(@RequestParam(value = "customerId") Long customerID,
                                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageCartItemRequest pageCartItemRequest = PageCartItemRequest.builder()
                .customerId(customerID)
                .page(page)
                .size(size)
                .build();

        Page<ListCartItemResponse> listCartItemResponses = cartItemService.listPaging(pageCartItemRequest);
        return WebResponse.<List<ListCartItemResponse>>builder()
                .data(listCartItemResponses.getContent())
                .paging(PagingResponse.builder()
                        .currentPage(listCartItemResponses.getNumber())
                        .totalPage(listCartItemResponses.getTotalPages())
                        .size(listCartItemResponses.getSize())
                        .build())
                .build();
    }
}
