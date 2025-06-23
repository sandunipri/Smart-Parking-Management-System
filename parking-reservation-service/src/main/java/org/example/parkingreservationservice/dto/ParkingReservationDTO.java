package org.example.parkingreservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component

public class ParkingReservationDTO {
    private Long id;
    private String email;
    private String vehicleNumber;
    private Long parkingSpaceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isActive;
}
