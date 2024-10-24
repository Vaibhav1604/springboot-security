package com.learning.demo.services;

import com.learning.demo.entities.Incident;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Incident Number");
        header.createCell(1).setCellValue("Subject");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Status");

        Incident incident = incidentService.getOneIncident(incId).getBody();

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(incId);
        row.createCell(1).setCellValue(incident.getIncSubject());
        row.createCell(2).setCellValue(incident.getIncDescription());
        row.createCell(3).setCellValue(incident.getStatus().toString());


        // Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"incident_report_" + incId + ".xlsx\"");

        // Write the workbook to the response output stream
        workbook.write(response.getOutputStream());
        workbook.close();

        return ResponseEntity.ok().build();
    }
}
