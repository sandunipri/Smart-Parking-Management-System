package org.example.vehicleservice.controller;

import org.example.vehicleservice.dto.ResponseDTO;
import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.entity.Vehicle;
import org.example.vehicleservice.feign.ClientVehicle;
import org.example.vehicleservice.repo.VehicleRepo;
import org.example.vehicleservice.service.VehicleService;
import org.example.vehicleservice.util.VarList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    private final ClientVehicle clientVehicle;

    public VehicleController(VehicleRepo vehicleRepo, VehicleService vehicleService, ClientVehicle clientVehicle) {
        this.vehicleService = vehicleService;
        this.clientVehicle = clientVehicle;
    }

    // Register new vehicle with user email
/*    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerVehicle(@RequestBody VehicleDTO vehicleDto) {
        int response = vehicleService.saveVehicle(vehicleDto);

        switch (response){
            case VarList.Created -> {
                return ResponseEntity.status(201)
                        .body(new ResponseDTO(VarList.Created, "User created successfully",vehicleDto ));
            }
            case VarList.Not_Acceptable -> {
                return ResponseEntity.status(406)
                        .body(new ResponseDTO(VarList.Not_Acceptable, "Email already used", null));
            }
            case VarList.Bad_Request -> {
                return ResponseEntity.status(400)
                        .body(new ResponseDTO(VarList.Bad_Request, "Invalid data provided", null));
            }
            default -> {
                return ResponseEntity.status(500)
                        .body(new ResponseDTO(VarList.Internal_Server_Error, "An error occurred", null));
            }
        }
    }*/

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

}
