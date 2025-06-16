package org.example.vehicleservice.repo;

import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    Vehicle findByLicensePlate(String licensePlate);

    List<Vehicle> findAllByEmail(String email);

}
