package org.example.msstudents.service;

import org.example.msstudents.model.request.CreateAdressRequest;
import org.example.msstudents.model.request.CreateCoursesRequest;
import org.example.msstudents.model.response.AdressResponse;
import org.example.msstudents.model.response.CoursesResponse;

import java.util.List;

public interface CoursesService {

    List<CoursesResponse> getAllCourses();

    CoursesResponse getCoursesById(Long id);

    void createCourses(CreateCoursesRequest coursesDto);

    void deleteCourses(Long id);

    void updateCourses(Long id, CreateCoursesRequest coursesDto);
}
