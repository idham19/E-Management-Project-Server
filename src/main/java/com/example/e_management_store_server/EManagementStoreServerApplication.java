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
import java.io.InputStream;

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
    public void run(String... args) {
        try {
            // Clean tables before injecting data
//            deviceService.cleanUpDeviceTable();
//            cartService.cleanUpCartTable();
//            userService.cleanUpUserTable();

            // Load JSON files from classpath (assets folder in resources)
            ClassPathResource deviceResource = new ClassPathResource("assets/devices.json");
            ClassPathResource cartResource = new ClassPathResource("assets/carts.json");

            // Check if the resources exist
            if (!deviceResource.exists()) {
                System.out.println("Device JSON file not found.");
                return;
            }
            if (!cartResource.exists()) {
                System.out.println("Cart JSON file not found.");
                return;
            }

            // Read the JSON data
            try (InputStream deviceStream = deviceResource.getInputStream()) {
                deviceService.importJsonData(deviceStream);
            } catch (IOException e) {
                System.out.println("Error reading device JSON: " + e.getMessage());
            }

            try (InputStream cartStream = cartResource.getInputStream()) {
                cartService.importJsonData(cartStream);
            } catch (IOException e) {
                System.out.println("Error reading cart JSON: " + e.getMessage());
            }

            System.out.println("Data imported from JSON files successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred during the data import process: " + e.getMessage());
        }
    }
}
