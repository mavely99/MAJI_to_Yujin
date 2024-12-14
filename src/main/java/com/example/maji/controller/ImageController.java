package com.example.maji.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ImageController {

    @PostMapping("/saveImage")
    public ResponseEntity<Map<String, String>> saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        // 저장 경로 설정
        String uploadDir = "src/main/resources/static/images/";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일명 생성
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "custom_image_" + timestamp + ".png";
        File savedFile = new File(uploadDir + fileName);

        // 파일 저장
        try (FileOutputStream fos = new FileOutputStream(savedFile)) {
            fos.write(file.getBytes());
        }

        // 저장된 파일의 URL 생성 (캐시 방지용 타임스탬프 추가)
        String imageUrl = "/images/" + fileName + "?timestamp=" + System.currentTimeMillis();

        // 응답 데이터 설정
        Map<String, String> response = new HashMap<>();
        response.put("savedImageURL", imageUrl);

        // 응답 반환
        return ResponseEntity.ok(response);
    }
}
