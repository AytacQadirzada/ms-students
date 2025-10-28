package org.example.msstudents.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.msstudents.dao.entity.AdressEntity;
import org.example.msstudents.dao.entity.StudentEntity;
import org.example.msstudents.dao.repository.StudentRepository;
import org.example.msstudents.exception.NotFoundException;
import org.example.msstudents.mapper.StudentMapper;
import org.example.msstudents.model.request.CreateStudentRequest;
import org.example.msstudents.model.response.StudentResponse;
import org.example.msstudents.service.StudentService;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final EntityManager entityManager;

    @Override
    public Page<StudentResponse> getAllStudents(int page, int size) {
        log.info("ActionLog.getAllStudents.start");
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> studentPage = studentRepository.findAll(pageable);
        Page<StudentResponse> responsePage = studentPage.map(studentMapper::toDto);
        log.info("ActionLog.getAllStudents.end");
        return responsePage;
    }


    @Override
    public StudentResponse getStudentById(Long id) {
        log.info("ActionLog.getStudentById.start id: {}", id);
        var entity = studentRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.getStudentById.error Student not found with id: {}", id);
            return new NotFoundException("Student not found with id: " + id);
        });
        var result = studentMapper.toDto(entity);
        log.info("ActionLog.getStudentById.end id: {}", id);
        return result;
    }

    @Override
    public List<StudentResponse> getStudent(String field, Object value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<StudentEntity> cq = cb.createQuery(StudentEntity.class);
        Root<StudentEntity> student = cq.from(StudentEntity.class);

        cq.select(student)
                .where(cb.equal(student.get(field), value));

        List<StudentEntity> resultList = entityManager.createQuery(cq).getResultList();

        return resultList.stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Override
    public List<StudentResponse> getStudentByAddress(String field, String value) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentEntity> cq = cb.createQuery(StudentEntity.class);
        Root<StudentEntity> student = cq.from(StudentEntity.class);

        Join<StudentEntity, AdressEntity> address = student.join("address");

        cq.select(student).where(cb.equal(address.get(field), value));

        List<StudentEntity> resultList = entityManager.createQuery(cq).getResultList();

        return resultList.stream()
                .map(studentMapper::toDto)
                .toList();
    }



    @Override
    public void createStudent(CreateStudentRequest studentDto) {
        log.info("ActionLog.addStudent.start email: {}", studentDto.getEmail());
        StudentEntity studentEntity = studentMapper.maptoEntity(studentDto);
        studentRepository.save(studentEntity);
        log.info("ActionLog.addStudent.end email: {}", studentDto.getEmail());
    }


    @Override
    public void deleteStudent(Long id) {
        log.info("ActionLog.deleteStudent.start id: {}", id);
        studentRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.deleteStudent.error Student not found with id: {}", id);
            return new NotFoundException("Student not found with id: " + id);
        });
        studentRepository.deleteById(id);
        log.info("ActionLog.deleteStudent.end id: {}", id);
    }

    @Override
    public void updateStudent(Long id, CreateStudentRequest studentDto) {
        log.info("ActionLog.updateStudent.start id: {}", id);
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.updateStudent.error Student not found with id: {}", id);
            return new NotFoundException("Student not found with id: " + id);
        });
        studentMapper.mapForUpdate(studentDto, studentEntity);
        studentEntity.setId(id);
        studentRepository.save(studentEntity);
        log.info("ActionLog.updateStudent.end id: {}", id);

    }

}
