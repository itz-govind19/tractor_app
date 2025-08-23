package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findByBooking_BookingId(Long bookingId);
}
