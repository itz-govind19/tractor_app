package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    private String serviceName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleDetail vehicle;
}
