package admin.myapp.com.authservice.repositoy;


import admin.myapp.com.authservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByFarmer_UserId(Long userId);
}
