package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBookingDTO {
    private LocalDateTime expectedDate;
    private String farmerName;
    private String farmerPhone;
    private Long serviceId;
    private Long vehicleId;
    private Double acres;
    private Double guntas;
    private Integer hours;
    private Integer minutes;
    private Double kilometers;
    private Double meters;
}
