package org.example.securityservice.service;

import org.example.securityservice.dto.ResponseDTO;
import org.example.securityservice.dto.UserDTO;
import org.example.securityservice.feign.UserSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserSecurity userSecurity;

    public UserSecurityService(UserSecurity userSecurity) {
        this.userSecurity = userSecurity;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            ResponseEntity<UserDTO> response = userSecurity.getUserByEmail(email);
            UserDTO userDTO =  response.getBody();

            if (userDTO == null) {
                throw new RuntimeException("User not found: " + email);
            }

            return new org.springframework.security.core.userdetails.User(
                    userDTO.getEmail(),
                    userDTO.getPassword(),
                    List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(userDTO.getRole()))
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to load user: " + email, e);
        }
    }

    public UserDTO getUser(String username) throws UsernameNotFoundException {
        try {
            ResponseEntity<UserDTO> response = userSecurity.getUserByEmail(username);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load user details: " + username, e);
 }
}

}
