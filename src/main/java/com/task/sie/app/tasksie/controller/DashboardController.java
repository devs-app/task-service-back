package com.task.sie.app.tasksie.controller;

import com.task.sie.app.tasksie.dto.DashboardData;
import com.task.sie.app.tasksie.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public List<DashboardData> getData(){
        return dashboardService.getDashboardData();
    }

}
