package com.example.travelweb.service.impl;

import com.example.travelweb.dto.RateDto;
import com.example.travelweb.entity.RateEntity;
import com.example.travelweb.entity.UploadEntity;
import com.example.travelweb.entity.UserEntity;
import com.example.travelweb.repository.RateRepository;
import com.example.travelweb.repository.UploadRepository;
import com.example.travelweb.repository.UserRepository;
import com.example.travelweb.service.RateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepo;
    private final UserRepository userRepo;
    private final UploadRepository uploadRepo;

    @Override
    public void save(RateDto rateDto) {
        RateEntity rate = rateDto.getId() != null ? rateRepo.findById(rateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with ID: " + rateDto.getId())) : new RateEntity();
        UploadEntity upload = uploadRepo.findById(rateDto.getUploadId())
                .orElseThrow(() -> new EntityNotFoundException("Upload not found with ID: " + rateDto.getUploadId()));
        UserEntity user = userRepo.findById(rateDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + rateDto.getUserId()));

        rate.setUpload(upload);
        rate.setUserId(user);
        rate.setRate(rateDto.getRate());
        rateRepo.save(rate);
    }

    @Override
    public Integer getRateByUploadId(Integer upload_id) {
        return rateRepo.getRateByUploadId(upload_id);
    }

    @Override
    public List<RateDto> findAllRatesByUserId(Integer userId) {
        return rateRepo.findByUserId_Id(userId).stream().map(rate -> new RateDto(rate.getId(), rate.getUserId().getId(), rate.getUpload().getId(), rate.getRate())).collect(Collectors.toList());
    }

    @Override
    public void updateRate(RateDto rateDto) {
        RateEntity rate = rateRepo.findById(rateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with ID: " + rateDto.getId()));
        rate.setRate(rateDto.getRate());
        rateRepo.save(rate);
    }

    @Override
    public void deleteRateById(Long id) {
        rateRepo.deleteById(id);
    }
}
