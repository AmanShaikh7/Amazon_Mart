package com.example.Shopping_portal.Repository;

import com.example.Shopping_portal.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
