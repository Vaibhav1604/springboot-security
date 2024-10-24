package com.learning.demo.services;

import com.learning.demo.entities.Attachments;
import com.learning.demo.entities.Incident;
import com.learning.demo.repositories.AttachmentsRepository;
import com.learning.demo.repositories.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class AttachmentsService {

    final String uploadDir = new ClassPathResource("static/Images").getFile().getAbsolutePath();

    @Autowired
    private AttachmentsRepository attachmentsRepository;

    @Autowired
    private IncidentRepository incidentRepository;

    public AttachmentsService() throws IOException {
    }

    public ResponseEntity<String> uploadFile(MultipartFile file, int incId) {
        Optional<Incident> optionalIncident = incidentRepository.findById(incId);
        Incident incident = optionalIncident.get();

        Attachments attachment = new Attachments();
        try {
            Files.copy(
                    file.getInputStream(),
                    Paths.get(uploadDir + File.separator + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );

            String path = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
//                    .path("/Images")
                    .path("/images/" + file.getOriginalFilename())
                    .toUriString();

            attachment.setFilePath(path);
            attachment.setIncident(incident);
            Attachments tentativeAttachment = attachmentsRepository.save(attachment);
            if (tentativeAttachment != null) {
                return ResponseEntity.ok("Attachments Uploaded in database");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("could not upload file");
        }
    }
}


//return ResponseEntity.ok("service activated");
//            byte data[] = new byte[is.available()];

//			FileOutputStream fos = new FileOutputStream(uploadDir+"//"+file.getOriginalFilename());
//			fos.write(data);
//			fos.flush();

//			fos.close();
//			Files.copy(is, null, null)
//			Files.copy(file.getInputStream(), Paths.get(uploadDir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);