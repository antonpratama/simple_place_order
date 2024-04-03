package com.anton.simpleplaceorder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersReponse {

    private UUID id;

    private Long customerId;

    private String status;

    private double totalPrice;
}
