package admin.myapp.com.authservice.repositoy;

import admin.myapp.com.authservice.entity.Payment;
import admin.myapp.com.authservice.entity.PaymentSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PaymentSettlementRepo  extends JpaRepository<PaymentSettlement, Long> {
    Collection<PaymentSettlement> findByPayment_PaymentId(Long paymentId);
}
