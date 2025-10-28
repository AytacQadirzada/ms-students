package org.example.msstudents.dao.repository;

import org.example.msstudents.dao.entity.AdressEntity;
import org.example.msstudents.dao.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdressRepository extends CrudRepository<AdressEntity, Long> {
    @Override
    List<AdressEntity> findAll();
}
