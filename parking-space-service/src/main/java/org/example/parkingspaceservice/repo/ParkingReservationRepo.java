package org.example.parkingspaceservice.repo;

import org.example.parkingspaceservice.entity.ParkingReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingReservationRepo extends JpaRepository<ParkingReservation, Long> {
}
