package org.example.msstudents.mapper;

import org.example.msstudents.dao.entity.CourseEntity;
import org.example.msstudents.dao.entity.StudentEntity;
import org.example.msstudents.model.request.CreateCoursesRequest;
import org.example.msstudents.model.request.CreateStudentRequest;
import org.example.msstudents.model.response.CoursesResponse;
import org.example.msstudents.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CoursesMapper {

    CoursesMapper INSTANCE = Mappers.getMapper(CoursesMapper.class);

    CourseEntity maptoEntity(CreateCoursesRequest request);

    CoursesResponse toDto(CourseEntity entity);

    void mapForUpdate(CreateCoursesRequest response,@MappingTarget CourseEntity entity);
}