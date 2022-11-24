package com.task.sie.app.tasksie.dto.company;

import com.task.sie.app.tasksie.model.company.Company;
import org.modelmapper.ModelMapper;

public class CompanyConverter {
    public static Company toModel(CompanyDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(CompanyDto.class,Company.class).addMappings(mapper -> {
            mapper.skip(Company::setCreatedAt);
        });
        Company entity= new Company();
        modelMapper.map(dto, entity);
        return entity;
    }
    public static CompanyDto toDto(Company entity) {
        ModelMapper modelMapper = new ModelMapper();
        CompanyDto dto = new CompanyDto();
        modelMapper.map(entity, dto);
        return dto;
    }
}
