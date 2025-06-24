package org.example.parkingreservationservice.service.impl;


import org.example.parkingreservationservice.dto.ParkingReservationDTO;
import org.example.parkingreservationservice.dto.ResponseDTO;
import org.example.parkingreservationservice.entity.ParkingReservation;
import org.example.parkingreservationservice.feign.ParkingSpaceFeignClient;
import org.example.parkingreservationservice.feign.UserFeignClient;
import org.example.parkingreservationservice.feign.VehicleFeignClient;
import org.example.parkingreservationservice.repo.ParkingReservationRepo;
import org.example.parkingreservationservice.service.ParkingReservationService;
import org.example.parkingreservationservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ParkingReservationImpl implements ParkingReservationService {

    @Autowired
    ModelMapper modelMapper;

    private final ParkingReservationRepo parkingReservationRepo;
    private final UserFeignClient userFeignClient;
    private final VehicleFeignClient vehicleFeignClient;
    private final ParkingSpaceFeignClient parkingSpaceFeignClient;

    public ParkingReservationImpl(ParkingReservationRepo parkingReservationRepo, UserFeignClient userFeignClient, VehicleFeignClient vehicleFeignClient, ParkingSpaceFeignClient parkingSpaceFeignClient) {
        this.parkingReservationRepo = parkingReservationRepo;
        this.userFeignClient = userFeignClient;
        this.vehicleFeignClient = vehicleFeignClient;
        this.parkingSpaceFeignClient = parkingSpaceFeignClient;
    }

    @Override
    public int reserveParking(ParkingReservationDTO parkingReservationDTO) {
        try {
            ResponseEntity<Boolean> userResponse = userFeignClient.isExistByEmail(parkingReservationDTO.getEmail());
            ResponseEntity<Boolean> vehicleResponse = vehicleFeignClient.getVehicleByNumber(parkingReservationDTO.getVehicleNumber());
            ResponseEntity<Boolean> parkingSpaceResponse = parkingSpaceFeignClient.existParkingSpaceById(parkingReservationDTO.getParkingSpaceId());

           //check if user, vehicle and parking space exist
            if (userResponse.getBody() != null && userResponse.getBody() &&
                vehicleResponse.getBody() != null && vehicleResponse.getBody() &&
                parkingSpaceResponse.getBody() != null && parkingSpaceResponse.getBody()) {

                //map dto to entity
                ParkingReservation parkingReservation = modelMapper.map(parkingReservationDTO, ParkingReservation.class);

                //save reservation
                parkingReservationRepo.save(parkingReservation);

                return VarList.Created;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User, Vehicle or Parking Space does not exist");
            }


       }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }



    }


}
