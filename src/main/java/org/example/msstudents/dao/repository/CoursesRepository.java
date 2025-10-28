package org.example.msstudents.dao.repository;

import org.example.msstudents.dao.entity.CourseEntity;
import org.example.msstudents.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<CourseEntity, Long> {
    @Override
    List<CourseEntity> findAll();

}
