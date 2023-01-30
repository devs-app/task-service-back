package com.task.sie.app.tasksie.dto.company.quote;

import lombok.Data;

import java.util.List;

@Data
public class CompanyQuoteRequest {
    private List<CompanyQuoteDto> quotes;
}
