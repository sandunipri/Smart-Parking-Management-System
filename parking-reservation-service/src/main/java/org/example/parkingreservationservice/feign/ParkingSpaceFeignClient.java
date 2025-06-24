package org.example.parkingreservationservice.feign;

import org.example.parkingreservationservice.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PARKING-SPACE-SERVICE")
public interface ParkingSpaceFeignClient {
    @GetMapping("/parking-space-service/api/v1/parking-space/{id}")
    ResponseEntity<Boolean> existParkingSpaceById(@PathVariable("id") Long id);
}


