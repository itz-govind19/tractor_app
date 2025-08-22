package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Table(name = "vehicle_detail")
public class VehicleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    private String vehicleType;

    private String vehicleNumber;

    private String model;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
