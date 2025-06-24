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
    private String email;//this is coming in user-service
    private String vehicleNumber;//this is coming form vehicle-service
    private Long parkingSpaceId;//this is coming from parking-space-service
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isActive;
}
