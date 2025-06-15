package org.example.vehicleservice.service;

import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.entity.Vehicle;

public interface VehicleService {
    int saveVehicle(VehicleDTO vehicleDto);
}
