package com.task.sie.app.tasksie.services;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.company.quote.CompanyQuoteRequest;

import java.math.BigDecimal;

public interface CompanyQuoteService {
    void configQuoteByCompany(Long companyId, CompanyQuoteRequest companyQuoteRequest) throws ResponseError;
    void updateQuoteFromRol(Long companyId, Long quoteId, BigDecimal quoteValue) throws ResponseError;
}
