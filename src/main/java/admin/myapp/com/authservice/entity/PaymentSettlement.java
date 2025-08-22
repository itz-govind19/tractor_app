package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_settlement")
public class PaymentSettlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settlementId;

    private Double actualPaidAmount;
    private String remarks;
    private LocalDateTime settlementDate;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
