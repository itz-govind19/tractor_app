package admin.myapp.com.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "queue")
@Data
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;

    private Integer positionNo;
    @Column(name = "adjusted", columnDefinition = "TINYINT(1)")
    private boolean adjusted;
    private LocalDateTime scheduledDate;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    private boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
