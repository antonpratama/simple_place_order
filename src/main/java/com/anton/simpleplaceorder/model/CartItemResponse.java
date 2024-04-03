package com.anton.simpleplaceorder.model;

import com.anton.simpleplaceorder.entity.Cart;
import com.anton.simpleplaceorder.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {

    private UUID id;

    private Long cartId;

    private Long productId;

    private int quantity;

    private double price;
}
