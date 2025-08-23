package admin.myapp.com.authservice.controller;

import admin.myapp.com.authservice.constant.Constants;
import admin.myapp.com.authservice.dto.CreateSettlementDTO;
import admin.myapp.com.authservice.dto.SettlementDTO;
import admin.myapp.com.authservice.service.PaymentSettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URL+Constants.BASE_URL_PAYMENT_SETTLEMENT)
public class PaymentSettlementController {

    @Autowired
    private PaymentSettlementService settlementService;

    @PostMapping
    public ResponseEntity<SettlementDTO> createSettlement(@RequestBody CreateSettlementDTO dto) {
        return ResponseEntity.ok(settlementService.createSettlement(dto));
    }

    @GetMapping
    public ResponseEntity<List<SettlementDTO>> getAll() {
        return ResponseEntity.ok(settlementService.getAllSettlements());
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<List<SettlementDTO>> getByPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(settlementService.getSettlementsByPayment(paymentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSettlement(@PathVariable Long id) {
        settlementService.deleteSettlement(id);
        return ResponseEntity.noContent().build();
    }
}
