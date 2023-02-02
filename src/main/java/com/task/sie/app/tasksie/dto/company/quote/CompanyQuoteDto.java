package com.task.sie.app.tasksie.dto.company.quote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyQuoteDto {
    private Long id;
    private String rol;
    private BigDecimal quote;
}
