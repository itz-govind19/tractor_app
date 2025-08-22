package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
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
}
