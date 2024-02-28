package com.example.travelweb.controller;

import com.example.travelweb.dto.BookingDTO;
import com.example.travelweb.entity.BookingEntity;
import com.example.travelweb.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;


    @PostMapping("/save")
    public String createData(@RequestBody BookingDTO bookingDTO){
        bookingService.save(bookingDTO);
        return "created data";
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingEntity>> getAllBookings() {
        List<BookingEntity> bookings = bookingService.findAll();
        return ResponseEntity.ok().body(bookings);
    }

    @GetMapping("/{id}")
    public Optional<BookingEntity> findById(@PathVariable("id") Integer id) {
        return bookingService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        bookingService.deleteById(id);
        return "Data deleted successfully";
    }

    @GetMapping("/user/{userId}") // Ensure the path variable name matches here
    public ResponseEntity<List<BookingEntity>> getBookingsByUserId(@PathVariable("userId") Integer userId) { // Match the name and annotate it with @PathVariable
        List<BookingEntity> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok().body(bookings);
    }
}
