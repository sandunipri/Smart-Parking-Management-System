package org.example.vehicleservice.controller;

import org.example.vehicleservice.dto.ResponseDTO;
import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.feign.ClientVehicle;
import org.example.vehicleservice.repo.VehicleRepo;
import org.example.vehicleservice.service.VehicleService;
import org.example.vehicleservice.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleRepo vehicleRepo, VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerVehicle(@RequestBody VehicleDTO vehicleDto) {
        int response = vehicleService.saveVehicle(vehicleDto);

        return switch (response) {
            case VarList.Created -> ResponseEntity.status(201)
                    .body(new ResponseDTO(VarList.Created, "Vehicle saved successfully", vehicleDto));
            case VarList.Not_Acceptable -> ResponseEntity.status(406)
                    .body(new ResponseDTO(VarList.Not_Acceptable, "Email not found in User Service", null));
            case VarList.Bad_Request -> ResponseEntity.status(400)
                    .body(new ResponseDTO(VarList.Bad_Request, "Invalid data provided", null));
            default -> ResponseEntity.status(500)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Server error", null));
        };
    }

    @GetMapping("/getAllVehicles")
    public ResponseEntity<ResponseDTO> getAllVehicles() {
        List<VehicleDTO> vehicleList = vehicleService.getAllVehicles();

        if (vehicleList.isEmpty()) {
            return ResponseEntity.status(204)
                    .body(new ResponseDTO(VarList.No_Content, "No vehicles found", null));
        } else {
            return ResponseEntity.status(200)
                    .body(new ResponseDTO(VarList.OK, "Vehicles retrieved successfully", vehicleList));

        }
    }

    @GetMapping("/get/{licensePlate}")
    public ResponseEntity<ResponseDTO> getVehicleById(@PathVariable String licensePlate){
        VehicleDTO vehicle = vehicleService.getVehicleLicenseId(licensePlate);
        if (vehicle == null) {
            return ResponseEntity.status(404)
                    .body(new ResponseDTO(VarList.Not_Found, "Vehicle not found", null));
        } else {
            return ResponseEntity.status(200)
                    .body(new ResponseDTO(VarList.OK, "Vehicle retrieved successfully", vehicle));
        }

    }

    @GetMapping("/getVehicleByEmail/{email}")
    public ResponseEntity<ResponseDTO> getVehicleByEmail(@PathVariable String email){
        List<VehicleDTO> vehicleList = vehicleService.getVehiclesByEmail(email);

        if (vehicleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseDTO(VarList.No_Content, "No vehicles found for this email", null));
        } else {
            return ResponseEntity.ok(
                    new ResponseDTO(VarList.OK, "Vehicles retrieved successfully", vehicleList));
        }
    }


}
