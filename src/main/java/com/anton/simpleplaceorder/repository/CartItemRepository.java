package com.anton.simpleplaceorder.repository;

import com.anton.simpleplaceorder.entity.Cart;
import com.anton.simpleplaceorder.entity.CartItem;
import com.anton.simpleplaceorder.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    CartItem findByCartAndProduct(Cart cart, Product product);

    List<CartItem> findByCart(Cart cart);

    Page<CartItem> findByCart(Cart cart, Pageable pageable);
}
