package com.example.e_management_store_server.service;

import com.example.e_management_store_server.model.User;
import com.example.e_management_store_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User postUser(User user) {
        return userRepository.save(user);
    }

    public void cleanUpUserTable() {
        jdbcTemplate.execute("SET SQL_SAFE_UPDATES = 0;");
        jdbcTemplate.execute("DELETE FROM users");
        jdbcTemplate.execute("ALTER TABLE users AUTO_INCREMENT = 1;");
        jdbcTemplate.execute("SET SQL_SAFE_UPDATES = 1;");
    }
}
