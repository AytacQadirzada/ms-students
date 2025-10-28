package org.example.msstudents.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.msstudents.dao.entity.AdressEntity;
import org.example.msstudents.dao.repository.AdressRepository;
import org.example.msstudents.dao.repository.StudentRepository;
import org.example.msstudents.exception.NotFoundException;
import org.example.msstudents.mapper.AdressMapper;
import org.example.msstudents.model.request.CreateAdressRequest;
import org.example.msstudents.model.response.AdressResponse;
import org.example.msstudents.service.AdressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {
    private final AdressRepository adressRepository;
    private final AdressMapper adressMapper;
    private final StudentRepository studentRepository;

    @Override
        public List<AdressResponse> getAllAdress() {
        log.info("ActionLog.getAllStudents.start");
        List<AdressEntity> adress = adressRepository.findAll();
        var responseList =  adress.stream().map(adressMapper::toDto).toList();
        log.info("ActionLog.getAllStudents.end");
        return responseList;
    }

    @Override
    public AdressResponse getAdressById(Long id) {
        log.info("ActionLog.getStudentById.start id: {}", id);
        var entity =  adressRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.getStudentById.error Student not found with id: {}", id);
                  return  new NotFoundException("Student not found with id: " + id);
        });
        var result = adressMapper.toDto(entity);
        log.info("ActionLog.getStudentById.end id: {}", id);
        return result;
    }

    @Override
    public void createAdress(CreateAdressRequest adressDto) {
        log.info("ActionLog.addStudent.start email: {}", adressDto);
        AdressEntity adressEntity = adressMapper.maptoEntity(adressDto);
        adressRepository.save(adressEntity);
        log.info("ActionLog.addStudent.end email: {}", adressDto);

    }

    @Override
    public void deleteAdress(Long id) {
        log.info("ActionLog.deleteStudent.start id: {}", id);
        adressRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.deleteStudent.error Student not found with id: {}", id);
            return new NotFoundException("Student not found with id: " + id);
        });
        adressRepository.deleteById(id);
        log.info("ActionLog.deleteStudent.end id: {}", id);
    }

    @Override
    public void updateAdress(Long id, CreateAdressRequest adressDto){
        log.info("ActionLog.updateStudent.start id: {}", id);
        AdressEntity adressEntity = adressRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.updateStudent.error Student not found with id: {}", id);
            return new NotFoundException("Student not found with id: " + id);
        });
        adressMapper.mapForUpdate(adressDto , adressEntity);
        adressEntity.setId(id);
        adressRepository.save(adressEntity);
        log.info("ActionLog.updateStudent.end id: {}", id);

    }
}
