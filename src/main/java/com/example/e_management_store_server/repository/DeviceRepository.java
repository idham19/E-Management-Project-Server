package com.example.e_management_store_server.repository;

import com.example.e_management_store_server.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {
}
