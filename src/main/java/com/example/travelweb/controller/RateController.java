package com.example.travelweb.controller;

import com.example.travelweb.dto.RateDto;
import com.example.travelweb.service.RateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody RateDto rateDto) {
        rateService.save(rateDto);
        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
    }

    @GetMapping("/{uploadId}")
    public ResponseEntity<Integer> getRateByUploadId(@PathVariable("uploadId") Integer uploadId) {
        Integer rate = rateService.getRateByUploadId(uploadId);
        if (rate != null) {
            return ResponseEntity.ok(rate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
