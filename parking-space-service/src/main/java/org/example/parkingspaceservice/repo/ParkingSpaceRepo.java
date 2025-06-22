package org.example.parkingspaceservice.repo;

import org.example.parkingspaceservice.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepo extends JpaRepository<ParkingSpace, Long> {
}
