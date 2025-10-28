package org.example.msstudents.dao.repository;

import org.example.msstudents.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    @Override
    List<StudentEntity> findAll();

}
