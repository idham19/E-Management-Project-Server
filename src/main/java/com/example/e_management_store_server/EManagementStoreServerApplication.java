package com.example.e_management_store_server;

import com.example.e_management_store_server.service.CartService;
import com.example.e_management_store_server.service.DeviceService;
import com.example.e_management_store_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class EManagementStoreServerApplication implements CommandLineRunner {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(EManagementStoreServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            //clean tables before injecting data
            deviceService.cleanUpDeviceTable();
            cartService.cleanUpCartTable();
            userService.cleanUpUserTable();

            // Load JSON file from classpath (assets folder in resources)
            File deviceJsonFile = new ClassPathResource("assets/devices.json").getFile();
            File cartJsonFile = new ClassPathResource("assets/carts.json").getFile();

            // Call the service to import the JSON data
            deviceService.importJsonData(deviceJsonFile.getAbsolutePath());
            cartService.importJsonData(cartJsonFile.getAbsolutePath());
            System.out.println("Data imported from JSON file in assets folder.");

        } catch (IOException e) {
            System.out.println("Failed to load JSON file from assets folder: " + e.getMessage());
        }
    }
}
