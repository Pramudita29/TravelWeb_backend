package com.example.travelweb.controller;

import com.example.travelweb.dto.UploadDTO;
import com.example.travelweb.entity.UploadEntity;
import com.example.travelweb.helper.ApiResponse;
import com.example.travelweb.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;
    private final ApiResponse apiResponse;


    @GetMapping
    public ResponseEntity<Map<String ,Object>> get(){
        return apiResponse.successResponse("Data fetch successfully",true,null,uploadService.findAll());
    }

    @GetMapping("/byCategory/{category}")
    public List<UploadEntity> searchByCategory(@PathVariable("category") String category) {
        return this.uploadService.searchByCategory(category);
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<Map<String, Object>> getTourPrice(@PathVariable("id") Integer id) {
        Optional<UploadEntity> tour = uploadService.findById(id);
        if (tour.isPresent()) {
            // Assuming the price field in UploadEntity is named "price"
            String price = tour.get().getPrice();
            return apiResponse.successResponse("Price fetched successfully", true, null, Map.of("price", price));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Tour not found", "success", false));
        }
    }

    @GetMapping("/tour-titles")
    public ResponseEntity<List<String>> getTourTitles() {
        List<UploadEntity> tours = uploadService.findAll();
        List<String> tourTitles = tours.stream()
                .map(UploadEntity::getTitle) // Fetch the title
                .collect(Collectors.toList());
        return ResponseEntity.ok(tourTitles);
    }

    @PostMapping("/save")
    public String createData(@RequestBody UploadDTO uploadDTO){
        uploadService.save(uploadDTO);
        return "created data";
    }

    @GetMapping("/{id}")
    public Optional<UploadEntity> findById(@PathVariable("id") Integer id) {
        return uploadService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        uploadService.deleteById(id);
        return "Data deleted successfully";
    }




}
