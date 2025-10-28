package org.example.msstudents.service;

import org.example.msstudents.dao.entity.StudentEntity;
import org.example.msstudents.model.request.CreateStudentRequest;
import org.example.msstudents.model.response.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {


    Page<StudentResponse> getAllStudents(int page, int size);

    StudentResponse getStudentById(Long id);

    void createStudent(CreateStudentRequest studentDto);

    void deleteStudent(Long id);

    void updateStudent(Long id, CreateStudentRequest studentDto);

    List<StudentResponse> getStudent(Map<String, Object> filters);

    List<StudentResponse> getStudentByAddress(Map<String, Object> filters);

}
