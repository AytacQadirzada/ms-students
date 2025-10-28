package org.example.msstudents.mapper;

import org.example.msstudents.dao.entity.AdressEntity;
import org.example.msstudents.dao.entity.StudentEntity;
import org.example.msstudents.model.request.CreateAdressRequest;
import org.example.msstudents.model.request.CreateStudentRequest;
import org.example.msstudents.model.response.AdressResponse;
import org.example.msstudents.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface AdressMapper {

                                    AdressMapper INSTANCE = Mappers.getMapper(AdressMapper.class);

    AdressEntity maptoEntity(CreateAdressRequest request);

    AdressResponse toDto(AdressEntity entity);

    public abstract void mapForUpdate(CreateAdressRequest response,@MappingTarget AdressEntity entity);
}