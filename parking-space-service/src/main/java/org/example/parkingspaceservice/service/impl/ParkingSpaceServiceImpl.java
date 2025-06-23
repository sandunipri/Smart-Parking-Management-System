package org.example.parkingspaceservice.service.impl;
import org.example.parkingspaceservice.dto.ParkingSpaceDTO;
import org.example.parkingspaceservice.entity.ParkingSpace;
import org.example.parkingspaceservice.feign.UserParkingSpace;
import org.example.parkingspaceservice.repo.ParkingSpaceRepo;
import org.example.parkingspaceservice.service.ParkingSpaceService;
import org.example.parkingspaceservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    private final ParkingSpaceRepo parkingSpaceRepo;
    private final UserParkingSpace userParkingSpace;

    @Autowired
    ModelMapper modelMapper;

    public ParkingSpaceServiceImpl(ParkingSpaceRepo parkingSpaceRepo, UserParkingSpace userParkingSpace) {
        this.parkingSpaceRepo = parkingSpaceRepo;
        this.userParkingSpace = userParkingSpace;
    }


    @Override
    public int saveParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        try {
            ResponseEntity<Boolean> response = userParkingSpace.isExistByEmail(parkingSpaceDTO.getEmail());
            if (!response.getBody()) {
                return VarList.Not_Found;
            }else {
                ParkingSpace parkingSpace = modelMapper.map(parkingSpaceDTO, ParkingSpace.class);
                parkingSpaceRepo.save(parkingSpace);
                return VarList.Created;
            }

        }catch (Exception e) {
            e.printStackTrace();
            return VarList.Internal_Server_Error;
        }

    }

    @Override
    public List<ParkingSpaceDTO> getAvailableParkingSpaces() {
        return parkingSpaceRepo.findAllByIsAvailable(true)
                .stream()
                .map(parkingSpace -> modelMapper.map(parkingSpace, ParkingSpaceDTO.class))
                .toList();
    }

    @Override
    public ParkingSpaceDTO getParkingSpaces(Long id) {
        ParkingSpace parkingSpace = parkingSpaceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));
        return modelMapper.map(parkingSpace, ParkingSpaceDTO.class);
    }

    @Override
    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        return modelMapper.map(parkingSpaceRepo.findAll(),new TypeToken<List<ParkingSpaceDTO>>(){}.getType());
    }

    @Override
    public ParkingSpaceDTO updateParkingSpace(Long id, ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace existingParkingSpace = parkingSpaceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));

        existingParkingSpace.setLocation(parkingSpaceDTO.getLocation());
        existingParkingSpace.setSlotNumber(parkingSpaceDTO.getSlotNumber());
        existingParkingSpace.setIsAvailable(parkingSpaceDTO.getIsAvailable());
        existingParkingSpace.setType(parkingSpaceDTO.getType());
        existingParkingSpace.setHourlyRate(parkingSpaceDTO.getHourlyRate());

        ParkingSpace updatedParkingSpace = parkingSpaceRepo.save(existingParkingSpace);
        return modelMapper.map(updatedParkingSpace, ParkingSpaceDTO.class);

    }

    @Override
    public boolean deleteParkingSpace(Long id) {
        if (parkingSpaceRepo.existsById(id)) {
            parkingSpaceRepo.deleteById(id);
            return true;
        } else {
            return false;
        }

    }


}
