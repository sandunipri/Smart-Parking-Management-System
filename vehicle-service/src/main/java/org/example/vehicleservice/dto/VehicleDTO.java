package org.example.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private Long VehicleId;
    private String vehicleNumber ;
    private String type;
    //this is user-service attribiute
    private String email;
}
