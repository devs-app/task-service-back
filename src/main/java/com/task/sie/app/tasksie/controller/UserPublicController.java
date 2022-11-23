package com.task.sie.app.tasksie.controller;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.security.AuthCredential;
import com.task.sie.app.tasksie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class UserPublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/change/password/{userId}")
    public ResponseEntity changePassword(@PathVariable Long userId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthCredential authCredential) throws ResponseError {
        return userService.login(authCredential);
    }
}
