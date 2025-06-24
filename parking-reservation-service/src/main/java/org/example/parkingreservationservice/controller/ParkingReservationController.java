package org.example.parkingreservationservice.controller;


import org.example.parkingreservationservice.dto.ParkingReservationDTO;
import org.example.parkingreservationservice.dto.ResponseDTO;
import org.example.parkingreservationservice.service.ParkingReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user/{userEmail}")
    public ResponseEntity<ResponseDTO> getUserReservations(@PathVariable String userEmail) {
        List<ParkingReservationDTO> parkingReservationDTO = parkingReservationService.getUserReservations(userEmail);

        return ResponseEntity.ok(
                new ResponseDTO(0, "User reservations retrieved successfully", parkingReservationDTO)
        );
    }

    @PutMapping("/end/{reservationId}")
    public ResponseEntity<ResponseDTO> endReservation(@PathVariable Long reservationId) {
        boolean ended = parkingReservationService.endReservation(reservationId);

        if (ended) {
            return ResponseEntity.ok(new ResponseDTO(0, "Reservation ended successfully", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(1, "Reservation not found or already ended", null));
        }
    }

    @GetMapping("/active/{userEmail}")
    public ResponseEntity<ResponseDTO> getActiveReservationByEmail(@PathVariable String userEmail) {
        ParkingReservationDTO activeReservation = parkingReservationService.getActiveReservationByEmail(userEmail);

        if (activeReservation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(404, "No active reservation found for user", null));
        }

        return ResponseEntity.ok(new ResponseDTO(200, "Active reservation retrieved", activeReservation));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<ResponseDTO> cancelReservation(@PathVariable Long id) {
        boolean isCancelled = parkingReservationService.cancelReservation(id);

        if (isCancelled) {
            return ResponseEntity.ok(new ResponseDTO(200, "Reservation cancelled successfully", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(404, "Reservation not found or already cancelled", null));
        }
    }





}


