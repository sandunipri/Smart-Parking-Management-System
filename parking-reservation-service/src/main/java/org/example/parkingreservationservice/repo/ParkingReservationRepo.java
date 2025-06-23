package org.example.parkingreservationservice.repo;

import org.example.parkingreservationservice.entity.ParkingReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingReservationRepo extends JpaRepository<ParkingReservation, Long> {
}
