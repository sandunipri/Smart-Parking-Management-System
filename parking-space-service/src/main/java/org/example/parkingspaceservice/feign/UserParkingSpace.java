package org.example.parkingspaceservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")
public interface UserParkingSpace {
    @GetMapping("/user-service/api/v1/user/check") // ✅ Leading slash, no service name prefix
    ResponseEntity<Boolean> isExistByEmail(@RequestParam("email") String email);
}
