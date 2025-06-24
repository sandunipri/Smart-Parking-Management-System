package org.example.parkingreservationservice.controller;


import org.example.parkingreservationservice.dto.ParkingReservationDTO;
import org.example.parkingreservationservice.dto.ResponseDTO;
import org.example.parkingreservationservice.service.ParkingReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parking-reservation")
public class ParkingReservationController {

    private final ParkingReservationService parkingReservationService;

    public ParkingReservationController(ParkingReservationService parkingReservationService) {
        this.parkingReservationService = parkingReservationService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<ResponseDTO> reserveParking(@RequestBody ParkingReservationDTO parkingReservationDTO) {
        int response = parkingReservationService.reserveParking(parkingReservationDTO);

        return ResponseEntity.ok(
                new ResponseDTO(response, "Parking reservation successful", parkingReservationDTO)
        );


    }

}


