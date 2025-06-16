package org.example.parkingspaceservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parking-space")
public class ParkingSpaceServiceController {

    @GetMapping("/all")
    public String getAllParkingSpaces() {
        return "All Parking Spaces";
    }

}
