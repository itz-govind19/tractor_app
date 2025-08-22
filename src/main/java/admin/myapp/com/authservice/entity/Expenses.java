package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private String description;
    private Double amount;
    private LocalDateTime expenseDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
