package org.example.parkingspaceservice.repo;

import org.example.parkingspaceservice.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepo extends JpaRepository<ParkingSpace, Long> {
    List<ParkingSpace> findAllByIsAvailable(boolean isAvailable);
}
