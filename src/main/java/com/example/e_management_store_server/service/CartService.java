package com.example.e_management_store_server.service;

import com.example.e_management_store_server.model.Cart;
import com.example.e_management_store_server.repository.CartRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void importJsonData(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Cart> carts = objectMapper.readValue(inputStream, new TypeReference<List<Cart>>() {});
        for (Cart cart : carts) {
            cartRepository.save(cart);
        }
    }

    public void cleanUpCartTable() {
        jdbcTemplate.execute("SET SQL_SAFE_UPDATES = 0;");
        jdbcTemplate.execute("DELETE FROM device");
        jdbcTemplate.execute("ALTER TABLE device AUTO_INCREMENT = 1;");
        jdbcTemplate.execute("SET SQL_SAFE_UPDATES = 1;");
    }

    public Cart postCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> findAllCart(){
        return cartRepository.findAll();
    }

    public Optional<Cart> findCartById(Long id){
        return  cartRepository.findById(id);
    }
    public void deleteCartById(Long id){
        cartRepository.deleteById(id);
    }
}
