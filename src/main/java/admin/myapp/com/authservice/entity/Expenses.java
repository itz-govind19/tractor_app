package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Data
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private String description;
    @Column(nullable = false)
    private BigDecimal amount;
    private LocalDateTime expenseDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "vehicle_Id")
    private VehicleDetail vehicleDetail;

    @Column(nullable = false)
    private boolean isDeleted = false;
}
