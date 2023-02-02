package com.task.sie.app.tasksie.dto.company.quote;

import com.task.sie.app.tasksie.dto.rol.RolConverter;
import com.task.sie.app.tasksie.model.company.CompanyQuote;
import org.modelmapper.ModelMapper;

public class CompanyQuoteConverter {
    public static CompanyQuote toModel(CompanyQuoteDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        CompanyQuote entity= new CompanyQuote();
        modelMapper.map(dto, entity);
        return entity;
    }
    public static CompanyQuoteDto toDto(CompanyQuote entity) {
        ModelMapper modelMapper = new ModelMapper();
        CompanyQuoteDto dto = new CompanyQuoteDto();
        modelMapper.map(entity, dto);
        dto.setRol(entity.getRol().getName().toString());
        return dto;
    }
}
