package com.task.sie.app.tasksie.dto.company.quote;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CompanyQuoteDto {
    private String rol;
    private BigDecimal quote;
}
