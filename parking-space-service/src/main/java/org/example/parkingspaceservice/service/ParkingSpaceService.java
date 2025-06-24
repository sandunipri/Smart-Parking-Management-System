package org.example.parkingspaceservice.service;

import org.example.parkingspaceservice.dto.ParkingSpaceDTO;

import java.util.List;

public interface ParkingSpaceService {
    int saveParkingSpace(ParkingSpaceDTO parkingSpaceDTO);

    List<ParkingSpaceDTO> getAvailableParkingSpaces();

    ParkingSpaceDTO getParkingSpaces(Long id);

    List<ParkingSpaceDTO> getAllParkingSpaces();

    ParkingSpaceDTO updateParkingSpace(Long id, ParkingSpaceDTO parkingSpaceDTO);

    boolean deleteParkingSpace(Long id);

    boolean existsById(Long id);
}
