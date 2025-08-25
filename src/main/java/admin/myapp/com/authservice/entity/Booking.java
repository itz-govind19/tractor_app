package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(nullable = false)
    private LocalDateTime expectedDate;  // Combined date & time

    private String farmerName;
    private String farmerPhone;

    private String status;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = true)
    private User farmer;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleDetail vehicle;

    @Column(name = "acres")
    private Double acres;  // Area in acres

    @Column(name = "guntas")
    private Double guntas; // Area in guntas (1 acre = 40 guntas)

    @Column(name = "hours")
    private Integer hours; // Time in hours

    @Column(name = "minutes")
    private Integer minutes; // Time in minutes

    @Column(name = "kilometers")
    private Double kilometers; // Distance in kilometers

    @Column(name = "meters")
    private Double meters; // Distance in meters

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    private boolean isDeleted = false;

    @Column(name = "payment_time")
    private LocalDateTime paymentTime;

    @Column(name = "reference_id")
    private String referenceId;

    @Transient
    private BigDecimal totalAmount;
}
