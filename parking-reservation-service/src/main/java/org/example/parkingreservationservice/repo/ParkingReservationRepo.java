package org.example.parkingreservationservice.repo;

import org.example.parkingreservationservice.entity.ParkingReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ParkingReservationRepo extends JpaRepository<ParkingReservation, Long> {
//    ParkingReservation findByEmail(String userEmail);
    List<ParkingReservation> findAllByEmail(String email);

    Optional<ParkingReservation> findTopByEmailAndIsActiveTrueOrderByStartTimeDesc(String userEmail);
}
