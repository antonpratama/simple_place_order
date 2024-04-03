package com.anton.simpleplaceorder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrdersRequest {

    private Long customerId;

    private String status;

    private double totalPrice;

    private List<CreateOrdersItemRequest> ordersItemRequests;
}
