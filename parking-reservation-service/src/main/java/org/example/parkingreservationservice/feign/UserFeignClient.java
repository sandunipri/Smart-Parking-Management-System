package org.example.parkingreservationservice.feign;

import org.example.parkingreservationservice.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {
    @GetMapping("/user-service/api/v1/user/check")
    ResponseEntity<Boolean> isExistByEmail(@RequestParam("email") String email);
}

