package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSettlementDTO {
    private Long paymentId;
    private BigDecimal actualPaidAmount;
    private String remarks;
}
