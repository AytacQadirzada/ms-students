package org.example.msstudents.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.msstudents.dao.entity.CourseEntity;
import org.example.msstudents.dao.entity.StudentEntity;
import org.example.msstudents.dao.repository.AdressRepository;
import org.example.msstudents.dao.repository.CoursesRepository;
import org.example.msstudents.dao.repository.StudentRepository;
import org.example.msstudents.exception.NotFoundException;
import org.example.msstudents.mapper.CoursesMapper;
import org.example.msstudents.mapper.StudentMapper;
import org.example.msstudents.model.request.CreateCoursesRequest;
import org.example.msstudents.model.request.CreateStudentRequest;
import org.example.msstudents.model.response.CoursesResponse;
import org.example.msstudents.model.response.StudentResponse;
import org.example.msstudents.service.CoursesService;
import org.example.msstudents.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {
    private final CoursesRepository coursesRepository;
    private final CoursesMapper coursesMapper;
    private final AdressRepository adressRepository;
    @Override
        public List<CoursesResponse> getAllCourses() {
        log.info("ActionLog.getAllStudents.start");
//        studentRepository.findAll().forEach(student -> student.getAdress().getId());
        List<CourseEntity> courses = coursesRepository.findAll();

        var responseList =  courses.stream().map(coursesMapper::toDto).toList();
        log.info("ActionLog.getAllStudents.end");
        return responseList;
    }

    @Override
    public CoursesResponse getCoursesById(Long id) {
        log.info("ActionLog.getStudentById.start id: {}", id);
        var entity =  coursesRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.getStudentById.error Student not found with id: {}", id);
                  return  new NotFoundException("Student not found with id: " + id);
        });
        var result = coursesMapper.toDto(entity);
        log.info("ActionLog.getStudentById.end id: {}", id);
        return result;
    }

    @Override
    public void createCourses(CreateCoursesRequest coursesDto) {
        log.info("ActionLog.addStudent.start email: {}", coursesDto.getName());
        CourseEntity coursesEntity = coursesMapper.maptoEntity(coursesDto);
        coursesRepository.save(coursesEntity);
        log.info("ActionLog.addStudent.end email: {}", coursesDto.getDescription());
    }


    @Override
    public void deleteCourses(Long id) {
        log.info("ActionLog.deleteStudent.start id: {}", id);
        coursesRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.deleteStudent.error Student not found with id: {}", id);
            return new NotFoundException("Student not found with id: " + id);
        });
        coursesRepository.deleteById(id);
        log.info("ActionLog.deleteStudent.end id: {}", id);
    }

    @Override
    public void updateCourses(Long id, CreateCoursesRequest coursesDto){
        log.info("ActionLog.updateStudent.start id: {}", id);
        CourseEntity coursesEntity = coursesRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.updateStudent.error Student not found with id: {}", id);
            return new NotFoundException("Student not found with id: " + id);
        });
        coursesMapper.mapForUpdate(coursesDto , coursesEntity);
        coursesEntity.setId(id);
        coursesRepository.save(coursesEntity);
        log.info("ActionLog.updateStudent.end id: {}", id);

    }
}
