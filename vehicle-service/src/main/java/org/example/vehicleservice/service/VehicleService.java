package org.example.vehicleservice.service;

import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    int saveVehicle(VehicleDTO vehicleDto);

    List<VehicleDTO> getAllVehicles();

    VehicleDTO getVehicleByNumber(String vehicleNumber);

    List<VehicleDTO> getVehiclesByEmail(String email);

    boolean existByVehicleNumber(VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);

    boolean deleteVehicle(String vehicleNumber);

}
