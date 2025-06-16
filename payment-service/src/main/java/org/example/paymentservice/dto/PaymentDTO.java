package org.example.paymentservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class PaymentDTO {
    Long id;
    Long userId;
    Long reservationId;
    Double amount;
    LocalDateTime paymentDate;
    String status;
    String method;
}
