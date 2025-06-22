package org.example.parkingspaceservice.controller;

import org.example.parkingspaceservice.dto.ParkingSpaceDTO;
import org.example.parkingspaceservice.dto.ResponseDTO;
import org.example.parkingspaceservice.service.ParkingSpaceSerice;
import org.example.parkingspaceservice.util.VarList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking-space")
public class ParkingSpaceServiceController {
    private final ParkingSpaceSerice parkingSpaceService;

    public ParkingSpaceServiceController(ParkingSpaceSerice parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

//    @PostMapping("/saveParkingSpace")
//    public ResponseEntity<ResponseDTO> saveParkingSpace(@RequestHeader("Authorization") String Authorization , @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
//
//
//    }



}
