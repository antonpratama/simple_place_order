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
public class ListCartItemResponse {

    private UUID id;

    private Long cartId;

    private Long productId;

    private String productName;

    private String productType;

    private int productPrice;

    private int productQuantity;

    private double totalPrice;
}
