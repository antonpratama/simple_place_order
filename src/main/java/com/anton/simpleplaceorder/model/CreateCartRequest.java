package com.anton.simpleplaceorder.model;

import com.anton.simpleplaceorder.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCartRequest {

    private Customer customer;
}
