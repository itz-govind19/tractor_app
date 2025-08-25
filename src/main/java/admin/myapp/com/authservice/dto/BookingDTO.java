package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long bookingId;
    private LocalDateTime expectedDate;
    private String farmerName;
    private String farmerPhone;
    private String status;
    private Long serviceId;
    private Long vehicleId;
    private Double acres;
    private Double guntas;
    private Integer hours;
    private Integer minutes;
    private Double kilometers;
    private Double meters;
    private String referenceId;
    private BigDecimal totalAmount;
}
