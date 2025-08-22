package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSettlementRepo  extends JpaRepository<Payment, Long> {
}
