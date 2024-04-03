package com.anton.simpleplaceorder.service;

import com.anton.simpleplaceorder.entity.Cart;
import com.anton.simpleplaceorder.entity.Customer;
import com.anton.simpleplaceorder.model.CreateCartRequest;
import com.anton.simpleplaceorder.model.CreateCustomerRequest;
import com.anton.simpleplaceorder.model.CustomerResponse;
import com.anton.simpleplaceorder.model.UpdateCustomerRequest;
import com.anton.simpleplaceorder.repository.CartRepository;
import com.anton.simpleplaceorder.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public void create(CreateCustomerRequest request){

        validationService.validate(request);

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setAddress(request.getAddress());

        customerRepository.save(customer);

        Cart cart = new Cart();
        cart.setCustomer(customer);

        cartRepository.save(cart);
    }

    public CustomerResponse get(Long customerId){
        Customer customerResponse = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return CustomerResponse.builder()
                .name(customerResponse.getName())
                .address(customerResponse.getAddress())
                .id(customerResponse.getId())
                .build();
    }

    @Transactional
    public CustomerResponse update(UpdateCustomerRequest request) {
        validationService.validate(request);

        Customer customer = customerRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        if (Objects.nonNull(request.getName())){
            customer.setName(request.getName());
        }

        if (Objects.nonNull(request.getAddress())){
            customer.setAddress(request.getAddress());
        }

        customerRepository.save(customer);

        return CustomerResponse.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .id(customer.getId())
                .build();
    }
}
