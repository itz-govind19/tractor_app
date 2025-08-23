package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTO {
    private Long paymentId;
    private BigDecimal amount;
    private String status;
    private String mode;
    private Long bookingId;
}
