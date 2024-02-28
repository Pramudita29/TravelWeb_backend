package com.example.travelweb.service;

import com.example.travelweb.dto.BookingDTO;
import com.example.travelweb.entity.BookingEntity;

import java.util.List;
import java.util.Optional;


public interface BookingService {

     void save(BookingDTO bookingDTO);

    List<BookingEntity> getBookingsByUserId(Integer userId);

    Optional<BookingEntity> findById(Integer id);

    void deleteById(Integer id);


    List<BookingEntity> findAll();
}
