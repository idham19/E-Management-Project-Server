package com.example.e_management_store_server.controller;

import com.example.e_management_store_server.model.Device;
import com.example.e_management_store_server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public Device updateDevice(@RequestBody Device device) {
        return deviceService.postDevice(device);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.findAllDevices();
    }

    //find device by ID
    @GetMapping("/{id}")
    public Optional<Device> getDeviceById(@PathVariable Long id) {
        return deviceService.findDeviceById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device deviceDetails) {
        Device updatedDevice = deviceService.updateDevice(id, deviceDetails);

        if (updatedDevice == null) {
            return ResponseEntity.notFound().build(); // Return 404 if the device is not found
        }

        return ResponseEntity.ok(updatedDevice); // Return 200 OK with the updated device
    }

    // delete device By Id
    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDeviceById(id);
    }

}
