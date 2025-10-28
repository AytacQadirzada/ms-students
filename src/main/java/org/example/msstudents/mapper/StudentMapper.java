package org.example.msstudents.mapper;

import org.example.msstudents.dao.entity.StudentEntity;
import org.example.msstudents.model.request.CreateStudentRequest;
import org.example.msstudents.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    @Mapping(source = "address", target = "address")
    StudentEntity maptoEntity(CreateStudentRequest request);

    StudentResponse toDto(StudentEntity entity);

    void mapForUpdate(CreateStudentRequest response,@MappingTarget StudentEntity entity);
}