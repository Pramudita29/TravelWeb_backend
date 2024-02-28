package com.example.travelweb;

import com.example.travelweb.entity.BookingEntity;
import com.example.travelweb.entity.UserEntity;
import com.example.travelweb.repository.BookingRepository;
import com.example.travelweb.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BookingRepoTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity createUserEntity() {
        UserEntity user = UserEntity.builder()
                .fullName("Test User")
                .username("testuser")
                .email("testuser@example.com")
                .password("TestPassword123") // In real scenarios, consider encoding
                .cpassword("TestPassword123")
                .build();

        return userRepository.save(user);
    }



    private BookingEntity createAndSaveBookingEntity(UserEntity user) {
        BookingEntity booking = new BookingEntity();
        booking.setUser(user);
        booking.setFullName("John Doe");
        booking.setEmail("johndoe@example.com");
        booking.setPhone("1234567890");
        booking.setTourDate("2023-09-01");
        booking.setTourName("Explore the Mountains");
        booking.setNumPersons(2);
        booking.setTotalPrice(300);
        return bookingRepository.save(booking);
    }

    @Test
    public void saveBooking() {
        UserEntity user = createUserEntity();
        BookingEntity savedBooking = createAndSaveBookingEntity(user);

        Assertions.assertThat(savedBooking.getId()).isNotNull();
    }

    @Test
    public void findBookingById() {
        UserEntity user = createUserEntity();
        BookingEntity savedBooking = createAndSaveBookingEntity(user);
        Optional<BookingEntity> foundBooking = bookingRepository.findById(savedBooking.getId());

        Assertions.assertThat(foundBooking).isPresent();
        Assertions.assertThat(foundBooking.get().getId()).isEqualTo(savedBooking.getId());
    }

    @Test
    public void findBookingsByUserId() {
        UserEntity user = createUserEntity();
        createAndSaveBookingEntity(user); // Create and save a booking for the user
        List<BookingEntity> bookings = bookingRepository.findByUserId(user.getId()); // Assuming this method exists and works as intended
        Assertions.assertThat(bookings).isNotEmpty();
        Assertions.assertThat(bookings.get(0).getUser()).isEqualTo(user);
    }


    @Test
    public void updateBooking() {
        UserEntity user = createUserEntity();
        BookingEntity booking = createAndSaveBookingEntity(user);
        booking.setTourName("Updated Explore the Mountains");
        BookingEntity updatedBooking = bookingRepository.save(booking);

        Assertions.assertThat(updatedBooking.getTourName()).isEqualTo("Updated Explore the Mountains");
    }

    @Test
    public void deleteBookingById() {
        UserEntity user = createUserEntity();
        BookingEntity booking = createAndSaveBookingEntity(user);
        Integer bookingId = booking.getId();
        bookingRepository.deleteById(bookingId);
        Optional<BookingEntity> deletedBooking = bookingRepository.findById(bookingId);

        Assertions.assertThat(deletedBooking).isEmpty();
    }
}
