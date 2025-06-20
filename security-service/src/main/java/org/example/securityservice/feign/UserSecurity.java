package org.example.securityservice.feign;

import org.example.securityservice.dto.ResponseDTO;
import org.example.securityservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:8081")
public interface UserSecurity {

    @GetMapping("user-service/api/v1/user/get/{email}")
    ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email);

}
