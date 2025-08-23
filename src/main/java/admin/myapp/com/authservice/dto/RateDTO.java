package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RateDTO {
    private Long rateId;
    private String unitType;
    private BigDecimal rateAmount;
    private Long serviceId;
}
