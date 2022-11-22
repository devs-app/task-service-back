package com.task.sie.app.tasksie.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/v1/security/user")
public class UserPublicController {

    @PostMapping("/change/password/{userId}")
    public ResponseEntity changePassword(@PathVariable Long userId){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
