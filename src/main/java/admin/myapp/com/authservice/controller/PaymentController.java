package admin.myapp.com.authservice.controller;

import admin.myapp.com.authservice.constant.Constants;
import admin.myapp.com.authservice.dto.CreatePaymentDTO;
import admin.myapp.com.authservice.dto.PaymentDTO;
import admin.myapp.com.authservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URL+Constants.BASE_URL_PAYMENTS)
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody CreatePaymentDTO dto) {
        return ResponseEntity.ok(paymentService.createPayment(dto));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentsByBooking(bookingId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
