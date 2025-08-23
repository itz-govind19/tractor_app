package admin.myapp.com.authservice.controller;

import admin.myapp.com.authservice.constant.Constants;
import admin.myapp.com.authservice.dto.CreateBookingDTO;
import admin.myapp.com.authservice.dto.BookingDTO;
import admin.myapp.com.authservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URL_RATE+Constants.BASE_URL_BOOKING)
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create a booking
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody CreateBookingDTO dto) {
        BookingDTO created = bookingService.createBooking(dto);
        return ResponseEntity.ok(created);
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // Get a booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("farmer/{id}")
    public ResponseEntity<List<BookingDTO>> getBookingByFarmerId(@PathVariable Long id) {
        List<BookingDTO> allBookingByFarmer = bookingService.getAllBookingByFarmer(id);
        return ResponseEntity.ok(allBookingByFarmer);
    }

    // Update an existing booking
    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody CreateBookingDTO dto) {
        BookingDTO updatedBooking = bookingService.updateBooking(id, dto);
        return ResponseEntity.ok(updatedBooking);
    }

    // Delete a booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
