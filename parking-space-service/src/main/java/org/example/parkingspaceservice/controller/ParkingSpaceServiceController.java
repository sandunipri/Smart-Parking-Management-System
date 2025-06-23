package org.example.parkingspaceservice.controller;

import org.example.parkingspaceservice.dto.ParkingSpaceDTO;
import org.example.parkingspaceservice.dto.ResponseDTO;
import org.example.parkingspaceservice.service.ParkingSpaceService;
import org.example.parkingspaceservice.util.VarList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-space")
public class ParkingSpaceServiceController {
    private final ParkingSpaceService parkingSpaceService;

    public ParkingSpaceServiceController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

/*    @PostMapping("/saveParkingSpace")
    public ResponseEntity<ResponseDTO> saveParkingSpace(@RequestHeader("Authorization") String Authorization,@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        // Extract the user’s email from the token.
        UserDTO userDTO = parkingSpaceService.getUserByToken(Authorization.substring(7));
        System.out.println("userDTO: " + userDTO);

        // Set the user in the parking space DTO
        parkingSpaceDTO.setUser(userDTO);

        // Save the parking space
        parkingSpaceService.saveParkingSpace(parkingSpaceDTO);

        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Parking Space Added Successfully", parkingSpaceDTO));

    }*/

    @PostMapping("/saveParkingSpace")
    public ResponseEntity<ResponseDTO> saveParkingSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        int response = parkingSpaceService.saveParkingSpace(parkingSpaceDTO);

        return switch (response) {
            case VarList.Created -> ResponseEntity.status(201)
                    .body(new ResponseDTO(VarList.Created, "Parking Space Added Successfully", parkingSpaceDTO));
            case VarList.Not_Acceptable -> ResponseEntity.status(406)
                    .body(new ResponseDTO(VarList.Not_Acceptable, "Parking Space Already Exists", null));
            case VarList.Bad_Request -> ResponseEntity.status(400)
                    .body(new ResponseDTO(VarList.Bad_Request, "Invalid data provided", null));
            default -> ResponseEntity.status(500)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Server error", null));
        };

    }

    @GetMapping("/spacesAvailable")
    public ResponseEntity<ResponseDTO> getAvailableParkingSpaces() {
        List<ParkingSpaceDTO> availableSpaces = parkingSpaceService.getAvailableParkingSpaces();
        if (availableSpaces.isEmpty()) {
            return ResponseEntity.status(204).body(new ResponseDTO(VarList.No_Content, "No available parking spaces found", null));
        }
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Available parking spaces retrieved successfully", availableSpaces));

   }

    @GetMapping("/space/{id}")
    public ResponseEntity<ResponseDTO> getParkingSpaceById(@PathVariable Long id) {
        ParkingSpaceDTO parkingSpaceDTO = parkingSpaceService.getParkingSpaces(id);

        if (parkingSpaceDTO == null) {
            return ResponseEntity.status(404)
                    .body(new ResponseDTO(VarList.Not_Found, "Parking Space Not Found", null));
        }

        return ResponseEntity.ok(
                new ResponseDTO(VarList.OK, "Parking Space retrieved successfully", parkingSpaceDTO));
    }

    @GetMapping("/allParkingSpaces")
    public ResponseEntity<ResponseDTO> getAllParkingSpaces() {
        List<ParkingSpaceDTO> allSpaces = parkingSpaceService.getAllParkingSpaces();
        if (allSpaces.isEmpty()) {
            return ResponseEntity.status(204).body(new ResponseDTO(VarList.No_Content, "No parking spaces found", null));
        }
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "All parking spaces retrieved successfully", allSpaces));
    }

    @PutMapping("/space/{id}")
    public ResponseEntity<ResponseDTO> updateParkingSpace(@PathVariable Long id, @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpaceDTO updatedParkingSpace = parkingSpaceService.updateParkingSpace(id, parkingSpaceDTO);

        if (updatedParkingSpace == null) {
            return ResponseEntity.status(404)
                    .body(new ResponseDTO(VarList.Not_Found, "Parking Space Not Found", null));
        }

        return ResponseEntity.ok(
                new ResponseDTO(VarList.OK, "Parking Space updated successfully", updatedParkingSpace));
    }

    @DeleteMapping("/space/{id}")
    public ResponseEntity<ResponseDTO> deleteParkingSpace(@PathVariable Long id) {
        boolean isDeleted = parkingSpaceService.deleteParkingSpace(id);

        if (!isDeleted) {
            return ResponseEntity.status(404)
                    .body(new ResponseDTO(VarList.Not_Found, "Parking Space Not Found", null));
        }

        return ResponseEntity.ok(
                new ResponseDTO(VarList.OK, "Parking Space deleted successfully", null));
    }
}
