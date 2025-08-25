package admin.myapp.com.authservice.service;

import admin.myapp.com.authservice.dto.BookingDTO;
import admin.myapp.com.authservice.dto.CreateBookingDTO;
import admin.myapp.com.authservice.entity.*;
import admin.myapp.com.authservice.repositoy.*;
import admin.myapp.com.authservice.util.RateCalculator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ServicesRepo servicesRepo;

    @Autowired
    private VehicalDetailRepo vehicleDetailRepo;

    @Autowired
    private PaymentRepo  paymentRepo;

    @Autowired
    private RateRepo rateRepo;

    // Create booking
    public BookingDTO createBooking(CreateBookingDTO dto) {
        Service service = servicesRepo.findById(dto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        VehicleDetail vehicle = vehicleDetailRepo.findById(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        Booking booking = new Booking();
        booking.setExpectedDate(dto.getExpectedDate());
        booking.setFarmerName(dto.getFarmerName());
        booking.setFarmerPhone(dto.getFarmerPhone());
        booking.setService(service);
        booking.setVehicle(vehicle);
        booking.setAcres(dto.getAcres());
        booking.setGuntas(dto.getGuntas());
        booking.setHours(dto.getHours());
        booking.setMinutes(dto.getMinutes());
        booking.setKilometers(dto.getKilometers());
        booking.setMeters(dto.getMeters());

        String s = generateReferenceNumber(dto);
        booking.setReferenceId(s);
        Booking save = bookingRepo.save(booking);

        Payment payment = new Payment();
        payment.setBooking(save);
        payment.setMode("CASH");
        payment.setStatus("PENDING");
        List<RateTable> byServiceServiceId = rateRepo.findByServiceServiceId(service.getServiceId());
        BigDecimal totalAmmount = RateCalculator.calculateRate(booking, service,byServiceServiceId);

        payment.setAmount(totalAmmount);
        paymentRepo.save(payment);
        save.setTotalAmount(totalAmmount);

        return convertToDTO(save);
    }

    public static String generateReferenceNumber(CreateBookingDTO dto) {
        String farmerPhone = dto.getFarmerPhone() != null ? dto.getFarmerPhone() : "0000";
        String farmerName = dto.getFarmerName() != null ? dto.getFarmerName() : "NA";

        // Extract last 4 digits of phone
        String last4Phone = farmerPhone.length() >= 4
                ? farmerPhone.substring(farmerPhone.length() - 4)
                : farmerPhone;

        // Get initials from farmer name
        String[] parts = farmerName.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                initials.append(Character.toUpperCase(part.charAt(0)));
            }
        }
        if (initials.length() == 0) {
            initials.append("NA");
        }

        // Timestamp + random for uniqueness (7 digits like your booking number)
        long currentTimestamp = System.currentTimeMillis();
        long bookingNumber = currentTimestamp % 1000000;
        Random random = new Random();
        int randomComponent = random.nextInt(10);
        bookingNumber = bookingNumber * 10 + randomComponent;

        // Final Reference Number
        return String.format("REF-%s%s-%07d", initials, last4Phone, bookingNumber);
    }

    // Get all bookings
    public List<BookingDTO> getAllBookings() {
        return bookingRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get a booking by ID
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        return convertToDTO(booking);
    }

    // Update an existing booking
    public BookingDTO updateBooking(Long id, CreateBookingDTO dto) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        // Fetch the service and vehicle again, in case they are updated
        Service service = servicesRepo.findById(dto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        VehicleDetail vehicle = vehicleDetailRepo.findById(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        // Update booking fields
        booking.setExpectedDate(dto.getExpectedDate());
        booking.setFarmerName(dto.getFarmerName());
        booking.setFarmerPhone(dto.getFarmerPhone());
        booking.setService(service);
        booking.setVehicle(vehicle);
        booking.setAcres(dto.getAcres());
        booking.setGuntas(dto.getGuntas());
        booking.setHours(dto.getHours());
        booking.setMinutes(dto.getMinutes());
        booking.setKilometers(dto.getKilometers());
        booking.setMeters(dto.getMeters());

        return convertToDTO(bookingRepo.save(booking));
    }

    // Delete a booking (soft or hard delete)
    public void deleteBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        // Soft delete (removes from DB)
        booking.setDeleted(true);
        bookingRepo.save(booking);
    }

    public List<BookingDTO> getAllBookingByFarmer(Long id) {
        return bookingRepo.findByFarmer_UserId(id)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    // Helper method to convert Booking to BookingDTO
    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setExpectedDate(booking.getExpectedDate());
        dto.setFarmerName(booking.getFarmerName());
        dto.setFarmerPhone(booking.getFarmerPhone());
        dto.setStatus(booking.getStatus());
        dto.setServiceId(booking.getService().getServiceId());
        dto.setVehicleId(booking.getVehicle().getVehicleId());
        dto.setAcres(booking.getAcres());
        dto.setGuntas(booking.getGuntas());
        dto.setHours(booking.getHours());
        dto.setMinutes(booking.getMinutes());
        dto.setKilometers(booking.getKilometers());
        dto.setMeters(booking.getMeters());
        dto.setReferenceId(booking.getReferenceId());
        dto.setTotalAmount(booking.getTotalAmount());
        return dto;
    }
}
