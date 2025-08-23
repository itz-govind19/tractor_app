package admin.myapp.com.authservice.service;

import admin.myapp.com.authservice.dto.CreatePaymentDTO;
import admin.myapp.com.authservice.dto.PaymentDTO;
import admin.myapp.com.authservice.entity.Booking;
import admin.myapp.com.authservice.entity.Payment;
import admin.myapp.com.authservice.repositoy.BookingRepo;
import admin.myapp.com.authservice.repositoy.PaymentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private BookingRepo bookingRepo;

    public PaymentDTO createPayment(CreatePaymentDTO dto) {
        Booking booking = bookingRepo.findById(dto.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());
        payment.setMode(dto.getMode());
        payment.setBooking(booking);

        return convertToDTO(paymentRepo.save(payment));
    }

    public List<PaymentDTO> getAllPayments() {
        return paymentRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PaymentDTO> getPaymentsByBooking(Long bookingId) {
        return paymentRepo.findByBooking_BookingId(bookingId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        return convertToDTO(payment);
    }

    public void deletePayment(Long id) {
        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        payment.setDeleted(true); // Soft delete
        paymentRepo.save(payment);
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setMode(payment.getMode());
        dto.setBookingId(payment.getBooking().getBookingId());
        return dto;
    }
}
