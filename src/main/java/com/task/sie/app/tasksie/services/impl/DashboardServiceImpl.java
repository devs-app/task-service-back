package com.task.sie.app.tasksie.services.impl;

import com.task.sie.app.tasksie.dto.DashboardData;
import com.task.sie.app.tasksie.enums.EnumStatus;
import com.task.sie.app.tasksie.repository.CompanyRepository;
import com.task.sie.app.tasksie.repository.UserRepository;
import com.task.sie.app.tasksie.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private final String nameUsers = "Usuarios";
    private final String nameCompanies = "Empresas";

    @Override
    public List<DashboardData> getDashboardData() {
        DashboardData users = DashboardData.builder()
                .name(nameUsers)
                .value(getUsersActives())
                .build();

        DashboardData companies = DashboardData.builder()
                .name(nameCompanies)
                .value(getCompaniesActives())
                .build();
        return List.of(companies, users);
    }

    private Long getCompaniesActives(){
        return companyRepository.countByStatus(EnumStatus.ACT);
    }
    private Long getUsersActives(){
        return userRepository.countByStatus(EnumStatus.ACT);
    }
}
