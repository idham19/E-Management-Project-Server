package com.example.e_management_store_server.repository;

import com.example.e_management_store_server.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
