package org.example.msstudents.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.msstudents.model.request.CreateStudentRequest;
import org.example.msstudents.model.response.StudentResponse;
import org.example.msstudents.service.ExcelService;
import org.example.msstudents.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Page<StudentResponse> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return studentService.getAllStudents(page, size);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    @GetMapping("/search")
    public List<StudentResponse> searchStudent(
            @RequestParam String field,
            @RequestParam String value) {
        return studentService.getStudent(field, value);
    }

    @GetMapping("/searchAddress")
    public List<StudentResponse> searchStudentByAddress(
            @RequestParam String field,
            @RequestParam String value) {
        return studentService.getStudentByAddress(field, value);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@Valid @RequestBody CreateStudentRequest studentDto) {
        studentService.createStudent(studentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody CreateStudentRequest studentDto) {
        studentService.updateStudent(id, studentDto);
    }

    private final ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            excelService.processExcel(file);
            return ResponseEntity.ok("Fayl uğurla yükləndi və emal olundu!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xəta: " + e.getMessage());
        }
    }


    @GetMapping("/export")
    public ResponseEntity<byte[]> exportStudents() throws IOException {
        byte[] excelData = excelService.exportStudentsToExcel();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelData);
    }


}
