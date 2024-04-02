package com.anton.simpleplaceorder.controller;

import com.anton.simpleplaceorder.entity.Customer;
import com.anton.simpleplaceorder.model.CreateCustomerRequest;
import com.anton.simpleplaceorder.model.CustomerResponse;
import com.anton.simpleplaceorder.model.UpdateCustomerRequest;
import com.anton.simpleplaceorder.model.WebResponse;
import com.anton.simpleplaceorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(
            path = "/api/customer/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> create(@RequestBody CreateCustomerRequest request){
        customerService.create(request);

        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/customer/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> get(@PathVariable("customerId") Long customerId){
        CustomerResponse customerResponse = customerService.get(customerId);

        return WebResponse.<CustomerResponse>builder().data(customerResponse).build();
    }

    @PutMapping(
            path = "/api/customer/{customerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> update(@RequestBody UpdateCustomerRequest request,
                                                @PathVariable("customerId") Long customerId){
        request.setId(customerId);

        CustomerResponse customerResponse = customerService.update(request);

        return WebResponse.<CustomerResponse>builder().data(customerResponse).build();
    }
}
