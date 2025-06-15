package org.example.vehicleservice.service.impl;

import feign.FeignException;
import org.example.userservice.dto.UserDTO;
import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.feign.ClientVehicle;
import org.example.vehicleservice.repo.VehicleRepo;
import org.example.vehicleservice.service.VehicleService;
import org.example.vehicleservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final ClientVehicle clientVehicle;
    private final VehicleRepo vehicleRepo;

    @Autowired
    ModelMapper modelMapper;

    public VehicleServiceImpl(ClientVehicle clientVehicle, VehicleRepo vehicleRepo) {
        this.clientVehicle = clientVehicle;
        this.vehicleRepo = vehicleRepo;
    }

/*    @Override
    public int saveVehicle(VehicleDTO vehicleDto) {
            ResponseEntity<Boolean> response = clientVehicle.isExistByEmail(vehicleDto.getEmail());

            Vehicle vehicle = modelMapper.map(vehicleDto, Vehicle.class);
            vehicle.setEmail(vehicleDto.getEmail()); // set email directly
            vehicleRepo.save(vehicle);

            if (response.getBody() != null && response.getBody()) {
                return VarList.Created; // Vehicle created successfully
            } else {
                return VarList.Not_Acceptable; // Email not found in User Service
            }
        }*/

    @Override
    public int saveVehicle(VehicleDTO vehicleDto) {
        try {
            ResponseEntity<Boolean> response = clientVehicle.isExistByEmail(vehicleDto.getEmail());

            if (response.getBody() != null && response.getBody()) {
                Vehicle vehicle = modelMapper.map(vehicleDto, Vehicle.class);
                vehicleRepo.save(vehicle);
                return VarList.Created;
            } else {
                return VarList.Not_Acceptable; // Email doesn't exist
            }
        } catch (Exception e) {
            e.printStackTrace(); // log the error
            return VarList.Internal_Server_Error;
        }
    }

}
