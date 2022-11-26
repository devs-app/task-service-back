package com.task.sie.app.tasksie.services;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.user.UserDto;
import com.task.sie.app.tasksie.model.user.User;
import com.task.sie.app.tasksie.security.AuthCredential;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> index();
    UserDto create(UserDto userDto);
    ResponseEntity login(AuthCredential presenter) throws ResponseError;
    UserDto update(Long userId, UserDto userDto) throws ResponseError;
}
