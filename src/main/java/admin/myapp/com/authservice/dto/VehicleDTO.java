package admin.myapp.com.authservice.dto;

import lombok.Data;

@Data
public class VehicleDTO {
    private Long vehicleId;
    private String vehicleType;
    private String vehicleNumber;
    private String model;
    private String userName;
}
