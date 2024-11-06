package com.learning.demo.services;

import com.learning.demo.entities.Incident;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Service
public class DownloadService {

    @Autowired
    private IncidentService incidentService;

    public ResponseEntity<Void> downloadReport(@PathVariable int incId, HttpServletResponse response) throws IOException {
        // Create a new workbook and a sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Incident Report");

        // Create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Incident Number");
        header.createCell(1).setCellValue("Subject");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Status");

        // Get the incident data
        Incident incident = incidentService.getOneIncident(incId).getBody();

        // Check if the incident is found
        if (incident == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Incident not found");
            return ResponseEntity.notFound().build();
        }

        // Create data row
        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(incId);
        row.createCell(1).setCellValue(incident.getIncSubject() != null ? incident.getIncSubject() : "N/A");
        row.createCell(2).setCellValue(incident.getIncDescription() != null ? incident.getIncDescription() : "N/A");
        row.createCell(3).setCellValue(incident.getStatus() != null ? incident.getStatus().toString() : "Unknown");

        // Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"incident_report_" + incId + ".xlsx\"");

        // Prepare the output stream and write the workbook data
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        byte[] fileBytes = baos.toByteArray();

        // Set the content length
        response.setContentLength(fileBytes.length);

        // Write the file to the response output stream
        try (ServletOutputStream outStream = response.getOutputStream()) {
            outStream.write(fileBytes);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception here
        } finally {
            workbook.close();
        }

        return ResponseEntity.ok().build();
    }
}
