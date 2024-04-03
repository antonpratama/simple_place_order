package com.anton.simpleplaceorder.controller;

import com.anton.simpleplaceorder.model.CreateOrdersRequest;
import com.anton.simpleplaceorder.model.OrdersReponse;
import com.anton.simpleplaceorder.model.WebResponse;
import com.anton.simpleplaceorder.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping(
            path = "/api/orders/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<OrdersReponse> create(@RequestBody CreateOrdersRequest request){
        OrdersReponse ordersReponse = ordersService.create(request);

        return WebResponse.<OrdersReponse>builder().data(ordersReponse).build();
    }
}
