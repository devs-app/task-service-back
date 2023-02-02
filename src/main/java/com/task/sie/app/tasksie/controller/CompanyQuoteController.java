package com.task.sie.app.tasksie.controller;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.company.quote.CompanyQuoteDto;
import com.task.sie.app.tasksie.dto.company.quote.CompanyQuoteRequest;
import com.task.sie.app.tasksie.services.CompanyQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/company-quote")
public class CompanyQuoteController {

    @Autowired
    public CompanyQuoteService companyQuoteService;

    @GetMapping("/{companyId}")
    public List<CompanyQuoteDto> index(@PathVariable Long companyId){
        return companyQuoteService.index(companyId);
    }

    @PostMapping("/{companyId}")
    public void configQuote(@PathVariable Long companyId, @RequestBody CompanyQuoteRequest companyQuoteRequest) throws ResponseError {
        companyQuoteService.configQuoteByCompany(companyId, companyQuoteRequest);
    }

    @PatchMapping("/{companyId}/{quoteId}/{quoteValue}")
    public void updateQuoteFromRol(@PathVariable Long companyId, @PathVariable Long quoteId, @PathVariable BigDecimal quoteValue) throws ResponseError {
        companyQuoteService.updateQuoteFromRol(companyId,quoteId,quoteValue);
    }

    @DeleteMapping("/{companyId}/{quoteId}")
    public void deleteQuote(@PathVariable Long companyId, @PathVariable Long quoteId) throws ResponseError {
        companyQuoteService.deleteQuoteFromRol(companyId, quoteId);
    }

}
