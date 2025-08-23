package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SettlementDTO {
    private Long settlementId;
    private BigDecimal actualPaidAmount; //paid ammount
    private BigDecimal actualAmount;     //ammount during booking
    private String remarks;
    private LocalDateTime settlementDate;
    private Long paymentId;
}

