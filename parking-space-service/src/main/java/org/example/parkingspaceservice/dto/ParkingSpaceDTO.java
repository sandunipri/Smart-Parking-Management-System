package org.example.parkingspaceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component

public class ParkingSpaceDTO {
    private Long id;
    private String location;
    private String slotNumber;
    private Boolean isAvailable;
    private String type;
    private Double hourlyRate;
    private String email;
}
