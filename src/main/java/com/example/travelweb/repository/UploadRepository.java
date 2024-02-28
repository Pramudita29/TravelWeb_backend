package com.example.travelweb.repository;

import com.example.travelweb.entity.UploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadRepository extends JpaRepository<UploadEntity, Integer> {

    List<UploadEntity> findByDifficulty(String difficulty);

    @Query(value = "select * from upload where category=?1", nativeQuery = true)
    List<UploadEntity> searchByCategory(String category);
}
