package com.example.travelweb;

import com.example.travelweb.entity.UploadEntity;
import com.example.travelweb.repository.UploadRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UploadRepoTest {

    @Autowired
    private UploadRepository uploadRepository;

    private UploadEntity createAndSaveUploadEntity(String category, String title, String difficulty) {
        UploadEntity upload = new UploadEntity();
        upload.setImage("image.jpg");
        upload.setTitle(title);
        upload.setDuration("7 days");
        upload.setMinPax(1);
        upload.setDifficulty(difficulty);
        upload.setDestination("Mountain");
        upload.setDescription("Explore the beautiful mountains.");
        upload.setProvince("Province");
        upload.setDistrict("District");
        upload.setRegion("Region");
        upload.setPrice("999");
        upload.setCategory(category);
        return uploadRepository.save(upload);
    }

    @Test
    public void saveUploadEntity() {
        UploadEntity savedUpload = createAndSaveUploadEntity("Adventure", "A Journey to the Mountains", "Medium");
        Assertions.assertThat(savedUpload.getId()).isNotNull();
        Assertions.assertThat(savedUpload.getCategory()).isEqualTo("Adventure");
    }

    @Test
    public void searchByCategory() {
        createAndSaveUploadEntity("Adventure", "Mountain Adventure", "Medium");
        createAndSaveUploadEntity("Leisure", "Leisurely Walks", "Easy");

        List<UploadEntity> foundUploads = uploadRepository.searchByCategory("Adventure");

        Assertions.assertThat(foundUploads).hasSize(1);
        Assertions.assertThat(foundUploads.get(0).getCategory()).isEqualTo("Adventure");
    }

    @Test
    public void updateUploadEntityDescription() {
        UploadEntity upload = createAndSaveUploadEntity("Adventure", "A Journey to the Mountains", "Medium");
        String newDescription = "Updated description for the adventure.";
        upload.setDescription(newDescription);
        uploadRepository.save(upload);

        UploadEntity updatedUpload = uploadRepository.findById(upload.getId()).orElseThrow();
        Assertions.assertThat(updatedUpload.getDescription()).isEqualTo(newDescription);
    }

    @Test
    public void deleteUploadEntityById() {
        UploadEntity upload = createAndSaveUploadEntity("Adventure", "A Journey to the Mountains", "Medium");
        Integer uploadId = upload.getId();
        uploadRepository.deleteById(uploadId);

        Optional<UploadEntity> deletedUpload = uploadRepository.findById(uploadId);
        Assertions.assertThat(deletedUpload).isEmpty();
    }

    @Test
    public void findUploadsByDifficulty() {
        String difficulty = "Hard";
        createAndSaveUploadEntity("Adventure", "Challenge in the Mountains", difficulty);
        createAndSaveUploadEntity("Adventure", "A Walk in the Park", "Easy");

        List<UploadEntity> foundUploads = uploadRepository.findByDifficulty(difficulty);

        Assertions.assertThat(foundUploads).hasSize(1); // Expecting to find only one upload with "Hard" difficulty
        Assertions.assertThat(foundUploads.get(0).getDifficulty()).isEqualTo(difficulty);
    }
}
