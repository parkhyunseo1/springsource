package com.example.movie.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Value("${com.example.movie.upload.path}")
    private String uploadPath;

    @GetMapping("/upload")
    public void getUpload() {
        log.info("upload 요청");
    }

    @PostMapping("/upload")
    public void postUpload(MultipartFile[] uploadFiles) {

        for (MultipartFile multipartFile : uploadFiles) {
            log.info("OriginalFilename{}", multipartFile.getOriginalFilename());
            log.info("Size {}", multipartFile.getSize());
            log.info("ContentType {}", multipartFile.getContentType());

            // 이미지 파일 여부 확인
            if (!multipartFile.getContentType().startsWith("image")) {
                return;
            }

            // 사용자가 올린 파일명
            String originName = multipartFile.getOriginalFilename();

            // 년/월/일
            String saveFolderPath = makeFolder();

            // 파일 저장 - uuid (중복파일 해결)
            String uuid = UUID.randomUUID().toString();
            // upload/2024/11/26/d8dc68e1-c785-49a2-b011-b2cb9f078873_1.jpg
            String saveName = uploadPath + File.separator + saveFolderPath + File.separator + uuid + "_" + originName;

            Path savePath = Paths.get(saveName);

            try {
                // transferTo : 폴더 저장
                multipartFile.transferTo(savePath);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private String makeFolder() {
        // 오늘 날짜 구하기
        LocalDate today = LocalDate.now();
        log.info("today {}", today);
        String dateStr = today.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));

        File dirs = new File(uploadPath, dateStr);
        if (!dirs.exists()) {
            dirs.mkdirs(); // 실제 폴더 생성
        }
        // 폴더 구조 : /or \\
        // c:/upload/1.jpg or c:\\upload\\1.jpg

        // 날짜나 시간,숫자, 특정 포맷을 지정해 보고 싶다? Formatter
        // SimpleDateFormat sdf = new Sim]pleDateFormat("YYYY/mm/dd:)
        // sdf.format(new Date());

        // 오늘날짜로 폴더 생성
        return dateStr;
    }

}
