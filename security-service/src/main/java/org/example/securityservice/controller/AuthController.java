package org.example.securityservice.controller;

import org.example.securityservice.dto.AuthDTO;
import org.example.securityservice.dto.LoginDTO;
import org.example.securityservice.dto.ResponseDTO;
import org.example.securityservice.dto.UserDTO;
import org.example.securityservice.service.UserSecurityService;
import org.example.securityservice.util.JwtUtil;
import org.example.securityservice.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@CrossOrigin
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserSecurityService userSecurityService;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserSecurityService userSecurityService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userSecurityService = userSecurityService;
    }


    @PostMapping(path = "/authenticate")
    public ResponseEntity<ResponseDTO> authenticateLogin(@RequestBody LoginDTO loginDTO) {
        UserDTO loadedUser = userSecurityService.getUser(loginDTO.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(VarList.Unauthorized, "Invalid Credentials", e.getMessage()));
        }


        if (loadedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        String token = jwtUtil.generateToken(loadedUser);
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        AuthDTO authDTO = new AuthDTO();
        authDTO.setToken(token);
        authDTO.setUserDTO(loadedUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(VarList.Created, "Authorization Success", authDTO));
}
}
