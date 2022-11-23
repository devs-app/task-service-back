package com.task.sie.app.tasksie.services;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.TokenDto;
import com.task.sie.app.tasksie.dto.user.UserDto;
import com.task.sie.app.tasksie.model.User;
import com.task.sie.app.tasksie.security.AuthCredential;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> index();
    User create(UserDto userDto);
    ResponseEntity login(AuthCredential presenter) throws ResponseError;
}
