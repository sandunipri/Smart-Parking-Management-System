package org.example.parkingreservationservice.service;

import org.example.parkingreservationservice.dto.ParkingReservationDTO;

public interface ParkingReservationService {
    int reserveParking(ParkingReservationDTO parkingReservationDTO);

}
