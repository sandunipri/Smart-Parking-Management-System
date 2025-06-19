package org.example.securityservice.feign;

import org.example.securityservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")

public interface UserSecurity {
    @GetMapping("user-service/api/v1/user/check")
    ResponseEntity<UserDTO> isExistByEmail(@RequestParam("email") String email);
}
