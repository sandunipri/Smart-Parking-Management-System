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
    Long id;
    String location;
    String slotNumber;
    Boolean isAvailable;
    String type;
    Double hourlyRate;
}
