package com.anton.simpleplaceorder.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageCartItemRequest {
    @NotNull
    private Long customerId;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
