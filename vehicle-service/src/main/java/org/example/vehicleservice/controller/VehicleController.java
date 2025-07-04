package org.example.vehicleservice.controller;

import org.example.vehicleservice.dto.ResponseDTO;
import org.example.vehicleservice.dto.VehicleDTO;
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

/*
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerVehicle(@RequestHeader("Authorization") String Authorization , @RequestBody VehicleDTO vehicleDto) {
        String email = vehicleService.getUserEmailByToken(Authorization.substring(7));

        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(VarList.Unauthorized, "Invalid Token", null));
        }

        vehicleDto.setEmail(email);

        // Save the vehicle
        int response = vehicleService.saveVehicle(vehicleDto);

        if (response == VarList.Created) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created, "Vehicle saved successfully", vehicleDto));
        } else if (response == VarList.Not_Acceptable) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResponseDTO(VarList.Not_Acceptable, "Email not found in User Service", null));
        } else if (response == VarList.Bad_Request) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(VarList.Bad_Request, "Invalid data provided", null));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Server error", null));
        }

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

*/

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

    @GetMapping("/get/{vehicleNumber}")
    public ResponseEntity<ResponseDTO> getVehicleById(@PathVariable String vehicleNumber){
        VehicleDTO vehicle = vehicleService.getVehicleByNumber(vehicleNumber);
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

    @PutMapping("/updateVehicle")
    public ResponseEntity<ResponseDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO){
        boolean isExist = vehicleService.existByVehicleNumber(vehicleDTO);

        if (!isExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, "Vehicle not found", null));
        }else {
            VehicleDTO updatedVehicle = vehicleService.updateVehicle(vehicleDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Vehicle updated successfully", vehicleDTO));

        }

    }

    @DeleteMapping("/deleteVehicle/{vehicleNumber}")
    public ResponseEntity<ResponseDTO> deleteVehicle(@PathVariable String vehicleNumber){
        boolean isDeleted = vehicleService.deleteVehicle(vehicleNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Vehicle deleted successfully", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, "Vehicle not found", null));
        }
    }

    @GetMapping("/getFeign/{vehicleNumber}")
    public ResponseEntity<Boolean> getVehicleByNumber(@PathVariable String vehicleNumber){
        boolean exists = vehicleService.existsByNumber(vehicleNumber);
        return ResponseEntity.ok(exists);
    }


}
