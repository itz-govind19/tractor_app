package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private BigDecimal amount;
    private String status;
    private String mode;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    private boolean isDeleted = false;
}
