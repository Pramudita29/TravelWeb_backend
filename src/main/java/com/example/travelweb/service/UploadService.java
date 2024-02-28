package com.example.travelweb.service;


import com.example.travelweb.dto.UploadDTO;
import com.example.travelweb.entity.UploadEntity;

import java.util.List;
import java.util.Optional;

public interface UploadService {

    List<UploadEntity> findAll();
    List<UploadEntity> searchByCategory(String category);

    Optional<UploadEntity> findById(Integer id);

    void deleteById(Integer id);

    void save(UploadDTO uploadDTO);

}
