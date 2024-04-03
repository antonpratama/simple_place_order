package com.anton.simpleplaceorder.repository;

import com.anton.simpleplaceorder.entity.Cart;
import com.anton.simpleplaceorder.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCustomer(Customer customer);
}
