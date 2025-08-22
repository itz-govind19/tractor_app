package admin.myapp.com.authservice.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "rate_table")
public class RateTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;

    private String unitType;

    private Double rateAmount;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}

