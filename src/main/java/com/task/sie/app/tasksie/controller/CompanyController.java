package com.task.sie.app.tasksie.controller;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.company.CompanyDto;
import com.task.sie.app.tasksie.dto.company.LegalRepresentativeDto;
import com.task.sie.app.tasksie.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/company")
public class CompanyController {

    @Autowired
    public CompanyService companyService;

    @GetMapping()
    public List<CompanyDto> index(){
        return  companyService.index();
    }

    @PostMapping("/create")
    public CompanyDto create(@RequestBody CompanyDto companyDto){
        return  companyService.create(companyDto);
    }

    @PatchMapping("/update/{companyId}")
    public CompanyDto update(@RequestBody CompanyDto companyDto, @PathVariable Long companyId) throws ResponseError {
        return  companyService.update(companyId,companyDto);
    }

    @PatchMapping("/activate/{companyId}")
    public Boolean activate(@RequestBody LegalRepresentativeDto legalRepresentativeDto, @PathVariable Long companyId) throws ResponseError {
        return companyService.activateCompany(companyId, legalRepresentativeDto);
    }

    @DeleteMapping("/delete/{companyId}")
    public CompanyDto delete( @PathVariable Long companyId) throws ResponseError {
        return companyService.delete(companyId);
    }

    @GetMapping("/validate/name")
    public boolean validateName(@RequestParam String name){
        return  companyService.existCompanyByName(name);
    }

    @GetMapping("/validate/commercial-name")
    public boolean validateCommercialName(@RequestParam String commercialName){
        return  companyService.existsByCommercialName(commercialName);
    }

    @GetMapping("/validate/document")
    public boolean validateDocument(@RequestParam String document){
        return  companyService.existCompanyByDocument(document);
    }

}
