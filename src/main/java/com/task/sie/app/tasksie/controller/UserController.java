package com.task.sie.app.tasksie.controller;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.user.UserDto;
import com.task.sie.app.tasksie.model.user.User;
import com.task.sie.app.tasksie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/security/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> index(){
        return userService.index();
    }

    @PostMapping("/create")
    public UserDto create(@RequestBody UserDto userDto){
        return userService.create(userDto);
    }

    @PatchMapping("/update/{userId}/")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable Long userId) throws ResponseError {
        return userService.update(userId,userDto);
    }
}
