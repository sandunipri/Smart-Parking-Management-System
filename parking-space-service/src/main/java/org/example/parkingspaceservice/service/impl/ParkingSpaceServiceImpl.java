package org.example.parkingspaceservice.service.impl;

import org.example.parkingspaceservice.dto.ParkingSpaceDTO;
import org.example.parkingspaceservice.entity.ParkingSpace;
import org.example.parkingspaceservice.repo.ParkingSpaceRepo;
import org.example.parkingspaceservice.service.ParkingSpaceSerice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceSerice {

    private final ParkingSpaceRepo parkingSpaceRepo;

    public ParkingSpaceServiceImpl(ParkingSpaceRepo parkingSpaceRepo) {
        this.parkingSpaceRepo = parkingSpaceRepo;
    }
    @Autowired
    ModelMapper modelMapper;

}
