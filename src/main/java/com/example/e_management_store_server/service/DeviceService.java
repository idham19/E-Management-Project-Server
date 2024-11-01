package com.example.e_management_store_server.service;

import com.example.e_management_store_server.model.Device;
import com.example.e_management_store_server.repository.DeviceRepository;
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
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void importJsonData(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Device> devices = objectMapper.readValue(inputStream, new TypeReference<List<Device>>() {});
        for (Device device : devices) {
            deviceRepository.save(device);
        }
    }

    public void cleanUpDeviceTable() {
        jdbcTemplate.execute("SET SQL_SAFE_UPDATES = 0;");
        jdbcTemplate.execute("DELETE FROM device");
        jdbcTemplate.execute("ALTER TABLE device AUTO_INCREMENT = 1;");
        jdbcTemplate.execute("SET SQL_SAFE_UPDATES = 1;");
    }

    public Device postDevice(Device device) {
        return deviceRepository.save(device);
    }

    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> findDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public void deleteDeviceById(Long id) {
        deviceRepository.deleteById(id);
    }

    public Device updateDevice(Long id, Device deviceDetails) {
        return deviceRepository.findById(id).map((device) -> {
            device.setType(deviceDetails.getType());
            device.setModel(deviceDetails.getModel());
            device.setBrand(deviceDetails.getBrand());
            device.setScreenSize(deviceDetails.getScreenSize());
            device.setPrice(deviceDetails.getPrice());
            device.setDiscount(deviceDetails.getDiscount());
            device.setImage(deviceDetails.getImage());
            return deviceRepository.save(device);
        }).orElse(null);
    }
}
