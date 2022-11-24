package com.task.sie.app.tasksie.services.impl;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.company.CompanyConverter;
import com.task.sie.app.tasksie.dto.company.CompanyDto;
import com.task.sie.app.tasksie.enums.EnumStatus;
import com.task.sie.app.tasksie.model.company.Company;
import com.task.sie.app.tasksie.model.user.Person;
import com.task.sie.app.tasksie.repository.CompanyRepository;
import com.task.sie.app.tasksie.repository.PersonRepository;
import com.task.sie.app.tasksie.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<CompanyDto> index() {
        return companyRepository.findAll().stream().map(CompanyConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CompanyDto create(CompanyDto dto) {
        Company company = CompanyConverter.toModel(dto);
        company.setStatus(EnumStatus.REG);
        return CompanyConverter.toDto(companyRepository.save(company));
    }

    @Override
    public CompanyDto update(Long companyId, CompanyDto dto) throws ResponseError {
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isEmpty())
            throw new ResponseError("La empresa no existe");

        company.get().setName(dto.getName());
        company.get().setCommercialName(dto.getCommercialName());
        company.get().setDocument(dto.getDocument());
        company.get().setPhone(dto.getPhone());
        company.get().setMobile(dto.getMobile());
        company.get().getLegalRepresentative().getPerson().setName(dto.getLegalRepresentative().getPerson().getName());
        company.get().getLegalRepresentative().getPerson().setLastName(dto.getLegalRepresentative().getPerson().getLastName());
        company.get().getLegalRepresentative().getPerson().setDocument(dto.getLegalRepresentative().getPerson().getDocument());
        return CompanyConverter.toDto(companyRepository.save(company.get()));
    }

    @Override
    public boolean existCompanyByName(String name) {
        return companyRepository.existsByName(name);
    }

    @Override
    public boolean existsByCommercialName(String commercialName) {
        return companyRepository.existsByCommercialName(commercialName);
    }

    @Override
    public boolean existCompanyByDocument(String document) {
        return companyRepository.existsByDocument(document);
    }

}
