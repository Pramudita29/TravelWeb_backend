package com.example.travelweb.repository;

import com.example.travelweb.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, Long> {

    @Query(value = "select avg(r.rate) from rate r where upload_id=?1", nativeQuery = true)
    Integer getRateByUploadId(Integer upload_id);

    List<RateEntity> findByUserId_Id(Integer userId);
}
