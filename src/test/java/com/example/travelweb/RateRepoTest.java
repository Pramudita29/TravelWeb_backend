package com.example.travelweb;

import com.example.travelweb.entity.RateEntity;
import com.example.travelweb.entity.UploadEntity;
import com.example.travelweb.entity.UserEntity;
import com.example.travelweb.repository.RateRepository;
import com.example.travelweb.repository.UploadRepository;
import com.example.travelweb.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class RateRepoTest {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadRepository uploadRepository;

    private UserEntity createUserEntity() {
        UserEntity user = new UserEntity();
        user.setFullName("Test User");
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("TestPassword123");
        user.setCpassword("TestPassword123");
        return userRepository.save(user);
    }

    private UploadEntity createUploadEntity() {
        UploadEntity upload = new UploadEntity();
        upload.setImage("testImage.jpg");
        upload.setTitle("Test Title");
        upload.setDuration("5 days");
        upload.setMinPax(3);
        upload.setDifficulty("Medium");
        upload.setDestination("Test Destination");
        upload.setDescription("Test Description");
        upload.setProvince("Test Province");
        upload.setDistrict("Test District");
        upload.setRegion("Test Region");
        upload.setPrice("1000");
        upload.setCategory("Adventure");
        return uploadRepository.save(upload);
    }

    private RateEntity createAndSaveRateEntity(UserEntity user, UploadEntity upload, Integer rateValue) {
        RateEntity rate = new RateEntity();
        rate.setUserId(user);
        rate.setUpload(upload);
        rate.setRate(rateValue);
        return rateRepository.save(rate);
    }

    @Test
    public void getRateByUploadId() {
        UserEntity user = createUserEntity();
        UploadEntity upload = createUploadEntity();

        createAndSaveRateEntity(user, upload, 5);
        createAndSaveRateEntity(user, upload, 3);

        Integer averageRate = rateRepository.getRateByUploadId(upload.getId().intValue());

        Assertions.assertThat(averageRate).isEqualTo(4); // The expected average might need adjustment based on actual DB rounding behavior
    }

    @Test
    public void saveRate() {
        UserEntity user = createUserEntity();
        UploadEntity upload = createUploadEntity();
        RateEntity rate = new RateEntity();
        rate.setUserId(user);
        rate.setUpload(upload);
        rate.setRate(5);

        RateEntity savedRate = rateRepository.save(rate);
        Assertions.assertThat(savedRate.getId()).isNotNull();
        Assertions.assertThat(savedRate.getRate()).isEqualTo(5);
    }

    @Test
    public void findAllRatesByUserId() {
        UserEntity user = createUserEntity();
        UploadEntity upload1 = createUploadEntity();
        UploadEntity upload2 = createUploadEntity(); // Assuming different uploads for diversity

        createAndSaveRateEntity(user, upload1, 5);
        createAndSaveRateEntity(user, upload2, 4);

        List<RateEntity> rates = rateRepository.findByUserId_Id(user.getId());
        Assertions.assertThat(rates).hasSize(2); // Verify that two rates are found for the user
    }

    @Test
    public void deleteRateById() {
        UserEntity user = createUserEntity();
        UploadEntity upload = createUploadEntity();
        RateEntity rate = createAndSaveRateEntity(user, upload, 5);

        rateRepository.deleteById(rate.getId());

        Optional<RateEntity> deletedRate = rateRepository.findById(rate.getId());
        Assertions.assertThat(deletedRate).isEmpty(); // Verify the rate is deleted
    }

    @Test
    public void updateRate() {
        UserEntity user = createUserEntity();
        UploadEntity upload = createUploadEntity();
        RateEntity rate = createAndSaveRateEntity(user, upload, 5);

        // Fetch, update, and save the rate
        rate.setRate(4);
        rateRepository.save(rate);

        RateEntity updatedRate = rateRepository.findById(rate.getId()).orElseThrow();
        Assertions.assertThat(updatedRate.getRate()).isEqualTo(4); // Verify the rate is updated
    }
}
