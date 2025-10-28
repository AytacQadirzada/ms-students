package org.example.msstudents.service;

import org.example.msstudents.model.request.CreateAdressRequest;
import org.example.msstudents.model.request.CreateStudentRequest;
import org.example.msstudents.model.response.AdressResponse;
import org.example.msstudents.model.response.StudentResponse;

import java.util.List;

public interface AdressService {

    List<AdressResponse> getAllAdress();

    AdressResponse getAdressById(Long id);

    void createAdress(CreateAdressRequest adressDto);

    void deleteAdress(Long id);

    void updateAdress(Long id, CreateAdressRequest adressDto);
}
