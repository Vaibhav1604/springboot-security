package com.learning.demo.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AttachmentsService {

    public ResponseEntity<String> uploadFile(MultipartFile file) {
        final String uploadDir = "D:\\PROJECT\\backend\\src\\main\\resources\\static\\Images";
//		return ResponseEntity.ok("service activated");
        try {

            InputStream is  = file.getInputStream();

//            byte data[] = new byte[is.available()];

//			FileOutputStream fos = new FileOutputStream(uploadDir+"//"+file.getOriginalFilename());
//			fos.write(data);
//			fos.flush();

//			fos.close();
//			Files.copy(is, null, null)
//			Files.copy(file.getInputStream(), Paths.get(uploadDir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
            Files.copy(
                    file.getInputStream(),
                    Paths.get(uploadDir+File.separator+file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );
            return ResponseEntity.ok("Upload successful");
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cant upload file");
        }
    }


}