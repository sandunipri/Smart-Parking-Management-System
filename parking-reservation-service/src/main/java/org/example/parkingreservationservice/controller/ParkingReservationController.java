package org.example.parkingreservationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@ResponseBody("api/v1/ParkingReservation")
public class ParkingReservationController {

    @GetMapping
    public String getParkingReservations() {
        // This method could return a list of parking reservations.
        return "List of parking reservations";
    }

}
