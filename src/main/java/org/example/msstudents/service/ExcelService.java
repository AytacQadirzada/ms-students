package org.example.msstudents.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.msstudents.dao.entity.StudentEntity;
import org.example.msstudents.dao.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {
    public void processExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                String ad = row.getCell(0).getStringCellValue();
                String soyad = row.getCell(1).getStringCellValue();
                int yas = (int) row.getCell(2).getNumericCellValue();
                String email = row.getCell(3).getStringCellValue();
                String phoneNumber = String.valueOf((int) row.getCell(4).getNumericCellValue());
                saveToDatabase(ad, soyad, yas, email, phoneNumber);
            }

        } catch (Exception e) {
            throw new RuntimeException("Excel oxunarkən xəta baş verdi: " + e.getMessage());
        }
    }

    private final StudentRepository studentRepository;

    private void saveToDatabase(String ad, String soyad, int yas, String email, String phoneNumber) {
        StudentEntity student = new StudentEntity();
        student.setFirstName(ad);
        student.setLastName(soyad);
        student.setAge(yas);
        student.setEmail(email);
        student.setPhoneNumber(phoneNumber);
        studentRepository.save(student);
    }

        public byte[] exportStudentsToExcel() throws IOException {
            List<StudentEntity> students = studentRepository.findAll();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Students");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Email");
            header.createCell(3).setCellValue("Phone");

            int rowNum = 1;
            for (StudentEntity student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getFirstName());
                row.createCell(2).setCellValue(student.getEmail());
                row.createCell(3).setCellValue(student.getPhoneNumber());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();
            return out.toByteArray();
        }


}
