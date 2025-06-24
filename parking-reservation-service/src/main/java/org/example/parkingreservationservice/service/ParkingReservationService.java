package org.example.parkingreservationservice.service;

import org.example.parkingreservationservice.dto.ParkingReservationDTO;

import java.util.List;

public interface ParkingReservationService {
    int reserveParking(ParkingReservationDTO parkingReservationDTO);

    List<ParkingReservationDTO> getUserReservations(String userEmail);

    boolean endReservation(Long reservationId);


    ParkingReservationDTO getActiveReservationByEmail(String userEmail);

    boolean cancelReservation(Long id);
}
