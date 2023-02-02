package com.task.sie.app.tasksie.services;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.company.quote.CompanyQuoteDto;
import com.task.sie.app.tasksie.dto.company.quote.CompanyQuoteRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CompanyQuoteService {
    List<CompanyQuoteDto> index(Long companyId);
    void configQuoteByCompany(Long companyId, CompanyQuoteRequest companyQuoteRequest) throws ResponseError;
    void updateQuoteFromRol(Long companyId, Long quoteId, BigDecimal quoteValue) throws ResponseError;
    void deleteQuoteFromRol(Long companyId, Long quoteId) throws ResponseError;
}
