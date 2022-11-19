package com.task.sie.app.tasksie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/security/user")
public class UserController {

    @GetMapping()
    public List<String> index(){
        return List.of("Hola", "Test");
    }
}
