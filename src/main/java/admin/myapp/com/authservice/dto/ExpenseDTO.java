package admin.myapp.com.authservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseDTO {
    private Long expenseId;
    private String description;
    private BigDecimal amount;
    private LocalDateTime expenseDate;
    private Long ownerId;
    private Long vehicleId; // NEW: show vehicle associated
}
