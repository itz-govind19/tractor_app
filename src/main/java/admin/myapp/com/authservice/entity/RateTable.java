package admin.myapp.com.authservice.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "rate_table")
public class RateTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;

    private String unitType;

    private BigDecimal rateAmount;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}

