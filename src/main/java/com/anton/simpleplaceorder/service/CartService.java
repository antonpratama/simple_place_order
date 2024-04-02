package com.anton.simpleplaceorder.service;

import com.anton.simpleplaceorder.entity.Cart;
import com.anton.simpleplaceorder.model.CartResponse;
import com.anton.simpleplaceorder.model.CreateCartRequest;
import com.anton.simpleplaceorder.model.WebResponse;
import com.anton.simpleplaceorder.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ValidationService validationService;

    public void create(CreateCartRequest request){

        Cart cart = new Cart();
        cart.setCustomer(request.getCustomer());

        cartRepository.save(cart);
    }


}
