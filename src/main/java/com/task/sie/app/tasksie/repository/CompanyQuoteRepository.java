package com.task.sie.app.tasksie.repository;

import com.task.sie.app.tasksie.model.company.Company;
import com.task.sie.app.tasksie.model.company.CompanyQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyQuoteRepository extends JpaRepository<CompanyQuote, Long> {
    List<CompanyQuote> findByCompany(Company company);
}