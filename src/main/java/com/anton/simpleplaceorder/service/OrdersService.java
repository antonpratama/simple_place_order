package com.anton.simpleplaceorder.service;

import com.anton.simpleplaceorder.entity.*;
import com.anton.simpleplaceorder.model.CreateOrdersItemRequest;
import com.anton.simpleplaceorder.model.CreateOrdersRequest;
import com.anton.simpleplaceorder.model.OrdersReponse;
import com.anton.simpleplaceorder.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersItemRepository ordersItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public OrdersReponse create(CreateOrdersRequest request){

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Orders orders = new Orders();
        orders.setCustomer(customer);
        orders.setStatus(request.getStatus());
        orders.setTotalPrice(request.getTotalPrice());

        ordersRepository.save(orders);

        List<CreateOrdersItemRequest> ordersItemRequests = request.getOrdersItemRequests();
        for (int i = 0; i < ordersItemRequests.size(); i++) {
            CartItem cartItem = cartItemRepository.findById(ordersItemRequests.get(i).getCartItemId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Item not found"));

            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            OrdersItem ordersItem = new OrdersItem();
            ordersItem.setOrders(orders);
            ordersItem.setProduct(product);
            ordersItem.setQuantity(cartItem.getQuantity());
            ordersItem.setPrice(cartItem.getPrice());
            ordersItemRepository.save(ordersItem);

            cartItemRepository.delete(cartItem);
        }

        return OrdersReponse.builder()
                .id(orders.getId())
                .customerId(customer.getId())
                .status(orders.getStatus())
                .totalPrice(orders.getTotalPrice())
                .build();
    }
}
