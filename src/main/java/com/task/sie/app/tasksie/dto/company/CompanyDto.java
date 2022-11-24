package com.task.sie.app.tasksie.dto.company;

import com.task.sie.app.tasksie.dto.BaseModelDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyDto extends BaseModelDto {
    private String name;
    private String commercialName;
    private String document;
    private String phone;
    private String mobile;
    private String logo;
    private LegalRepresentativeDto legalRepresentative;
}
