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

    @GetMapping("/get/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404)
                    .body(new ResponseDTO(VarList.Not_Found, "User not found", null));
        } else {
            return ResponseEntity.status(200)
                    .body(new ResponseDTO(VarList.OK, "User retrieved successfully", user));
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

    @GetMapping("/check")
    public ResponseEntity<Boolean> isExistByEmail(@RequestParam String email) {
        boolean isExist = userService.existByEmail(email);
        return ResponseEntity.ok(isExist);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO) {
        boolean isExist = userService.existByEmail(userDTO.getEmail());

        if (!isExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, "User not found", null));
        }else {
            UserDTO updatedUser = userService.updateUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "User updated successfully", updatedUser));
        }





    }


}
