package com.anton.simpleplaceorder.service;

import com.anton.simpleplaceorder.entity.Cart;
import com.anton.simpleplaceorder.entity.CartItem;
import com.anton.simpleplaceorder.entity.Customer;
import com.anton.simpleplaceorder.entity.Product;
import com.anton.simpleplaceorder.model.CartItemResponse;
import com.anton.simpleplaceorder.model.CreateCartItemRequest;
import com.anton.simpleplaceorder.model.ListCartItemResponse;
import com.anton.simpleplaceorder.model.PageCartItemRequest;
import com.anton.simpleplaceorder.repository.CartItemRepository;
import com.anton.simpleplaceorder.repository.CartRepository;
import com.anton.simpleplaceorder.repository.CustomerRepository;
import com.anton.simpleplaceorder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Transactional
    public CartItemResponse create(CreateCartItemRequest request){

        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.setPrice(cartItem.getPrice() + request.getPrice());

            cartItemRepository.save(cartItem);
        }
        else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(request.getPrice());

            cartItemRepository.save(cartItem);
        }


        return CartItemResponse.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCart().getId())
                .productId(cartItem.getProduct().getId())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }

    public void delete(UUID cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Item not found"));

        cartItemRepository.delete(cartItem);
    }

    public List<ListCartItemResponse> list(Long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Cart cart = cartRepository.findByCustomer(customer);

        List<CartItem> cartItemList = cartItemRepository.findByCart(cart);

        return cartItemList.stream().map(cartItem -> toListCartItemResponse(cartItem)).toList();
    }

    public Page<ListCartItemResponse> listPaging(PageCartItemRequest request){

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Cart cart = cartRepository.findByCustomer(customer);

        Page<CartItem> cartItemList = cartItemRepository.findByCart(cart, pageable);
        List<ListCartItemResponse> cartItemResponses = cartItemList.getContent().stream()
                .map(cartItem -> toListCartItemResponse(cartItem))
                .collect(Collectors.toList());

        return new PageImpl<>(cartItemResponses, pageable, cartItemList.getTotalElements());
    }

    private ListCartItemResponse toListCartItemResponse(CartItem cartItem){
        Product product = productRepository.findById(cartItem.getProduct().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return ListCartItemResponse.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCart().getId())
                .productId(product.getId())
                .productName(product.getName())
                .productType(product.getType())
                .productPrice(product.getPrice())
                .productQuantity(cartItem.getQuantity())
                .totalPrice(cartItem.getPrice())
                .build();
    }
}
