package com.task.sie.app.tasksie.repository;

import com.task.sie.app.tasksie.enums.EnumStatus;
import com.task.sie.app.tasksie.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByName(String name);
    boolean existsByCommercialName(String name);
    boolean existsByDocument(String name);
    Long countByStatus(EnumStatus status);
}
