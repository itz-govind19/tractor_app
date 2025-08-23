package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateRateDTO {
    private String unitType;
    private BigDecimal rateAmount;
    private Long serviceId;
}
