package com.example.travelweb.service;

import com.example.travelweb.dto.RateDto;
import java.util.List;

public interface RateService {
    void save(RateDto rateDto);
    Integer getRateByUploadId(Integer upload_id);
    List<RateDto> findAllRatesByUserId(Integer userId);
    void updateRate(RateDto rateDto);
    void deleteRateById(Long id);
}
