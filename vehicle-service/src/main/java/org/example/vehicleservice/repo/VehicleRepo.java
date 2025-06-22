package org.example.vehicleservice.repo;

import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    Vehicle findByVehicleNumber(String vehicleNumber);
    List<Vehicle> findAllByEmail(String email);
    boolean existsByVehicleNumber(String vehicleNumber);
}
