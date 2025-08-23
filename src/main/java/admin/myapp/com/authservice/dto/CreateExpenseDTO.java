package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateExpenseDTO {
    private String description;
    private BigDecimal amount;
    private LocalDateTime expenseDate;
    private Long ownerId;
    private Long vehicleId; // NEW: link to vehicle
}
