package org.example.parkingreservationservice.feign;

import org.example.parkingreservationservice.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VEHICLE-SERVICE")
public interface VehicleFeignClient {
    @GetMapping("/vehicle-service/api/v1/vehicle/getFeign/{vehicleNumber}")
    ResponseEntity<Boolean> getVehicleByNumber(@PathVariable("vehicleNumber") String vehicleNumber);
}


