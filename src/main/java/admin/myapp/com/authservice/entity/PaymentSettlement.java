package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_settlement")
@Data
public class PaymentSettlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settlementId;

    // Existing fields
    private BigDecimal actualPaidAmount;
    private String remarks;
    private LocalDateTime settlementDate;
    private BigDecimal remainingAmount;
    private BigDecimal extraEarnedAmount;

    @Column(name = "mark_fully_paid", columnDefinition = "TINYINT(1)")
    private boolean markFullyPaid = false;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    private boolean isDeleted = false;

    // Additional essential fields for tractor service

    // Transaction reference
    @Column(name = "receipt_number", unique = true)
    private String receiptNumber; // Manual receipt number for cash transactions

    // Split payment tracking
    @Column(name = "installment_number")
    private Integer installmentNumber; // 1st payment, 2nd payment, etc.

    @Column(name = "is_partial_payment", columnDefinition = "TINYINT(1)")
    private boolean isPartialPayment = false; // True if this is a partial payment

    @Column(name = "payment_date") // Actual date when this split amount was paid
    private LocalDateTime paymentDate;

    @Column(name = "total_paid_so_far", precision = 10, scale = 2)
    private BigDecimal totalPaidSoFar; // Running total after this payment

    // Status tracking
    @Enumerated(EnumType.STRING)
    @Column(name = "settlement_status")
    private SettlementStatus settlementStatus; // COMPLETED, REFUNDED, PARTIALLY_REFUNDED

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType; // PAYMENT, REFUND

    // Refund specific fields (since refunds are applicable)
    @Column(name = "refund_amount", precision = 10, scale = 2)
    private BigDecimal refundAmount;

    @Column(name = "refund_date")
    private LocalDateTime refundDate;

    @Column(name = "refund_reason")
    private String refundReason;

    // Person handling the transaction
    @Column(name = "collected_by")
    private String collectedBy; // Name/ID of person who collected cash

    @Column(name = "refunded_by")
    private String refundedBy; // Name/ID of person who processed refund

    // Audit fields (minimal but important)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (settlementStatus == null) {
            settlementStatus = SettlementStatus.COMPLETED;
        }
        if (transactionType == null) {
            transactionType = TransactionType.PAYMENT;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// Simplified enums for tractor service
enum SettlementStatus {
    PENDING,           // When partial payment is made but booking not fully paid
    COMPLETED,         // When full payment is received
    REFUNDED,
    PARTIALLY_REFUNDED
}

enum TransactionType {
    PAYMENT,
    PARTIAL_PAYMENT,   // For split payments
    REFUND
}
