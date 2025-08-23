package admin.myapp.com.authservice.service;

import admin.myapp.com.authservice.dto.CreateSettlementDTO;
import admin.myapp.com.authservice.dto.SettlementDTO;
import admin.myapp.com.authservice.entity.Payment;
import admin.myapp.com.authservice.entity.PaymentSettlement;
import admin.myapp.com.authservice.repositoy.PaymentRepo;
import admin.myapp.com.authservice.repositoy.PaymentSettlementRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentSettlementService {

    @Autowired
    private PaymentSettlementRepo settlementRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    public SettlementDTO createSettlement(CreateSettlementDTO dto) {
        Payment payment = paymentRepo.findById(dto.getPaymentId())
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        PaymentSettlement settlement = new PaymentSettlement();
        settlement.setPayment(payment);
        settlement.setActualPaidAmount(dto.getActualPaidAmount());
        settlement.setRemarks(dto.getRemarks());
        settlement.setSettlementDate(LocalDateTime.now());

        return convertToDTO(settlementRepo.save(settlement));
    }

    public List<SettlementDTO> getAllSettlements() {
        return settlementRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SettlementDTO> getSettlementsByPayment(Long paymentId) {
        return settlementRepo.findByPayment_PaymentId(paymentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteSettlement(Long id) {
        PaymentSettlement settlement = settlementRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Settlement not found"));
        settlement.setDeleted(true);
        settlementRepo.save(settlement);
    }

    private SettlementDTO convertToDTO(PaymentSettlement settlement) {
        SettlementDTO dto = new SettlementDTO();
        dto.setSettlementId(settlement.getSettlementId());
        dto.setActualPaidAmount(settlement.getActualPaidAmount());
        dto.setRemarks(settlement.getRemarks());
        dto.setSettlementDate(settlement.getSettlementDate());
        dto.setPaymentId(settlement.getPayment().getPaymentId());
        return dto;
    }
}
