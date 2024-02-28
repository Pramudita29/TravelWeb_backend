package com.example.travelweb.service.impl;

import com.example.travelweb.dto.BookingDTO;
import com.example.travelweb.entity.BookingEntity;
import com.example.travelweb.entity.UserEntity;
import com.example.travelweb.repository.BookingRepository;
import com.example.travelweb.repository.UserRepository;
import com.example.travelweb.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepo;
    private final UserRepository userRepository;

    @Override
    public List<BookingEntity> getBookingsByUserId(Integer userId) {
        return bookingRepo.findByUserId(userId);
    }

    @Override
    public Optional<BookingEntity> findById(Integer id) {
        return bookingRepo.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        bookingRepo.deleteById(id);
    }

    @Override
    public List<BookingEntity> findAll() {
        return bookingRepo.findAll();
    }



    @Transactional
    @Override
    public void save(BookingDTO bookingDTO) {
        BookingEntity booking = new BookingEntity();
        if (bookingDTO.getId() != null) {
            booking = bookingRepo.findById(bookingDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Data not found for ID: " + bookingDTO.getId()));
        }

        booking.setEmail(bookingDTO.getEmail());
        booking.setFullName(bookingDTO.getFullName());
        booking.setPhone(bookingDTO.getPhone());
        booking.setTourDate(bookingDTO.getTourDate());
        booking.setTourName(bookingDTO.getTourName());
        booking.setNumPersons(bookingDTO.getNumPersons());
        booking.setTotalPrice(bookingDTO.getTotalPrice());

        UserEntity user = userRepository.findById(bookingDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + bookingDTO.getUserId()));
        booking.setUser(user);

        bookingRepo.save(booking);
    }
}
