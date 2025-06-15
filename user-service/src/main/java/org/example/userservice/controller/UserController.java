package org.example.userservice.controller;

import org.example.userservice.dto.ResponseDTO;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.service.UserService;
import org.example.userservice.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        List<UserDTO> userList = userService.getAllUsers();
        if (userList.isEmpty()) {
            return ResponseEntity.status(204)
                    .body(new ResponseDTO(VarList.No_Content, "No users found", null));
        } else {
            return ResponseEntity.status(200)
                    .body(new ResponseDTO(VarList.OK, "Users retrieved successfully", userList));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody UserDTO userDTO) {
        int response = userService.saveUser(userDTO);

        switch (response) {
            case VarList.Created -> {
                return ResponseEntity.status(201)
                        .body(new ResponseDTO(VarList.Created, "User created successfully", userDTO));
            }
            case VarList.Not_Acceptable -> {
                return ResponseEntity.status(406)
                        .body(new ResponseDTO(VarList.Not_Acceptable, "Email already used", null));
            }
            case VarList.Bad_Request -> {
                return ResponseEntity.status(400)
                        .body(new ResponseDTO(VarList.Bad_Request, "Invalid data provided", null));
            }
            default -> {
                return ResponseEntity.status(500)
                        .body(new ResponseDTO(VarList.Internal_Server_Error, "An error occurred", null));
            }
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String email) {
        boolean isDeleted = userService.deleteUser(email);
        if (isDeleted) {
            return ResponseEntity.status(200)
                    .body(new ResponseDTO(VarList.OK, "User deleted successfully", null));
        } else {
            return ResponseEntity.status(404)
                    .body(new ResponseDTO(VarList.Not_Found, "User not found", null));
        }
    }

/*    @GetMapping("/api/v1/user/{email}")
    public ResponseEntity<Boolean> isExistByEmail(@PathVariable String email) {
       try {
            boolean exists = userService.existsByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(exists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
        System.out.println("hnbvnbvjhj");
        try {
            boolean isExist = userService.existByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(isExist);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }*/

    @GetMapping("/check")
    public ResponseEntity<Boolean> isExistByEmail(@RequestParam String email) {
        boolean isExist = userService.existByEmail(email);
        return ResponseEntity.ok(isExist);
    }



}
