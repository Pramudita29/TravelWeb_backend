package com.example.travelweb.service.impl;

import com.example.travelweb.dto.UploadDTO;
import com.example.travelweb.entity.UploadEntity;
import com.example.travelweb.repository.UploadRepository;
import com.example.travelweb.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final UploadRepository uploadRepo;

    @Override
    public List<UploadEntity> findAll() {
        return uploadRepo.findAll();
    }

    @Override
    public List<UploadEntity> searchByCategory(String category) {
        return uploadRepo.searchByCategory(category);
    }

    @Override
    public Optional<UploadEntity> findById(Integer id) {
        return uploadRepo.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        uploadRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void save(UploadDTO uploadDto) {
        UploadEntity upload = uploadDto.getId() != null ?
                uploadRepo.findById(uploadDto.getId()).orElse(new UploadEntity()) : new UploadEntity();

        upload.setCategory(uploadDto.getCategory());
        upload.setDestination(uploadDto.getDestination());
        upload.setDescription(uploadDto.getDescription());
        upload.setDistrict(uploadDto.getDistrict());
        upload.setDuration(uploadDto.getDuration());
        upload.setDifficulty(uploadDto.getDifficulty());
        upload.setImage(uploadDto.getImage());
        upload.setProvince(uploadDto.getProvince());
        upload.setRegion(uploadDto.getRegion());
        upload.setMinPax(uploadDto.getMinPax());
        upload.setTitle(uploadDto.getTitle());
        upload.setPrice(uploadDto.getPrice());
        uploadRepo.save(upload); // Persist the entity to the database
    }
}
