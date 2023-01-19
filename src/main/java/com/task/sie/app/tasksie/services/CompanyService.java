package com.task.sie.app.tasksie.services;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.company.CompanyDto;
import com.task.sie.app.tasksie.dto.company.LegalRepresentativeDto;
import com.task.sie.app.tasksie.dto.user.UserDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> index();
    CompanyDto create(CompanyDto dto);
    CompanyDto update(Long companyId, CompanyDto dto) throws ResponseError;
    CompanyDto delete(Long companyId) throws ResponseError;
    Boolean activateCompany(Long companyId, LegalRepresentativeDto legalRepresentativeDto) throws ResponseError;
    boolean existCompanyByName(String name);
    boolean existsByCommercialName(String name);
    boolean existCompanyByDocument(String name);
}
