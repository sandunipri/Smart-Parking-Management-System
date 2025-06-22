package org.example.vehicleservice.service.impl;

import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.feign.ClientVehicle;
import org.example.vehicleservice.repo.VehicleRepo;
import org.example.vehicleservice.service.VehicleService;
import org.example.vehicleservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return modelMapper.map(vehicleRepo.findAll(), new TypeToken<List<VehicleDTO>>(){}.getType());
    }

    @Override
    public VehicleDTO getVehicleByNumber(String vehicleNumber) {
        Vehicle vehicle = vehicleRepo.findByVehicleNumber(vehicleNumber);
        return modelMapper.map(vehicle, VehicleDTO.class);
    }


    @Override
    public List<VehicleDTO> getVehiclesByEmail(String email) {
        List<Vehicle> vehicleList = vehicleRepo.findAllByEmail(email); // or findAllByUserEmail
        return vehicleList.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existByVehicleNumber(VehicleDTO vehicleDTO) {
        return vehicleRepo.existsByVehicleNumber(vehicleDTO.getVehicleNumber());

    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepo.findByVehicleNumber(vehicleDTO.getVehicleNumber());
        if (vehicle != null) {
            vehicle.setType(vehicleDTO.getType());
            vehicleRepo.save(vehicle);
            return modelMapper.map(vehicle, VehicleDTO.class);
        }
        return null;


    }

    @Override
    public boolean deleteVehicle(String vehicleNumber) {
        Vehicle vehicle = vehicleRepo.findByVehicleNumber(vehicleNumber);
        if (vehicle != null) {
            vehicleRepo.delete(vehicle);
            return true;
        }
        return false;

    }

}
