package org.example.vehicleservice.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "USER-SERVICE")
public interface ClientVehicle {
    @GetMapping("user-service/api/v1/user/check")
    ResponseEntity<Boolean> isExistByEmail(@RequestParam("email") String email);
}

