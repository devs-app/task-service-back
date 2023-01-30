package com.task.sie.app.tasksie.services.impl;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.company.quote.CompanyQuoteRequest;
import com.task.sie.app.tasksie.enums.EnumRol;
import com.task.sie.app.tasksie.enums.EnumStatus;
import com.task.sie.app.tasksie.model.company.Company;
import com.task.sie.app.tasksie.model.company.CompanyQuote;
import com.task.sie.app.tasksie.model.user.Rol;
import com.task.sie.app.tasksie.repository.CompanyQuoteRepository;
import com.task.sie.app.tasksie.repository.CompanyRepository;
import com.task.sie.app.tasksie.repository.RolRepository;
import com.task.sie.app.tasksie.services.CompanyQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyQuoteServiceImpl implements CompanyQuoteService {

    @Autowired
    public CompanyQuoteRepository companyQuoteRepository;
    @Autowired
    public CompanyRepository companyRepository;
    @Autowired
    public RolRepository rolRepository;

    private static final String COMPANY_NOT_EXISTS = "La empresa no existe";
    private static final String COMPANY_QUOTE_NOT_EXISTS = "La cuota no existe";
    private static final String COMPANY_NOT_CORRESPOND = "La empresa no corresponde al parametro enviado";
    private static final String QUOTE_NOT_VALID = "El numero de usuarios es mayor al total de cuota permitida";

    @Override
    public void configQuoteByCompany(Long companyId, CompanyQuoteRequest companyQuoteRequest) throws ResponseError {
        Optional<Company> company = companyRepository.findById(companyId);

        if(company.isEmpty())
            throw new ResponseError(COMPANY_NOT_EXISTS);

        companyQuoteRequest.getQuotes().forEach(i ->{
            CompanyQuote companyQuote = new CompanyQuote();
            Rol rol = rolRepository.findByName(EnumRol.valueOf(i.getRol()));
            companyQuote.setCompany(company.get());
            companyQuote.setRol(rol);
            companyQuote.setQuote(i.getQuote());
            companyQuoteRepository.save(companyQuote);
        });
    }

    @Override
    public void updateQuoteFromRol(Long companyId, Long quoteId, BigDecimal quoteValue) throws ResponseError {

        if(validateUsersFromCompany(companyId,quoteId, quoteValue)){
            throw new ResponseError(QUOTE_NOT_VALID);
        }

        Optional<CompanyQuote> companyQuote = companyQuoteRepository.findById(quoteId);
        if(companyQuote.isEmpty())
            throw new ResponseError(COMPANY_QUOTE_NOT_EXISTS);

        if(!Objects.equals(companyQuote.get().getCompany().getId(), companyId)){
            throw new ResponseError(COMPANY_NOT_CORRESPOND);
        }

        companyQuote.get().setQuote(quoteValue);
        companyQuoteRepository.save(companyQuote.get());
    }

    private boolean validateUsersFromCompany(Long companyId, Long quoteId,BigDecimal quoteValue) throws ResponseError {
        Optional<Company> company = companyRepository.findById(companyId);

        if(company.isEmpty())
            throw new ResponseError(COMPANY_NOT_EXISTS);

        long usersActive =  company.get().getUsers().stream()
                .filter(c -> c.getStatus() == EnumStatus.ACT)
                .count();

        BigDecimal totalQuotes = totalQuoteCompany(company.get(), quoteId, quoteValue);

        if(usersActive > quoteValue.add(totalQuotes).longValue() ){
            return true;
        }

        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private BigDecimal totalQuoteCompany(Company company, Long quoteId,BigDecimal quoteValue ){
        List<CompanyQuote> quotes = companyQuoteRepository.findByCompany(company);

        return quotes.stream()
                .filter(c -> !Objects.equals(c.getId(), quoteId))
                .map(CompanyQuote::getQuote)
                .reduce(BigDecimal.ZERO, BigDecimal::add).add(quoteValue);
    }

}
