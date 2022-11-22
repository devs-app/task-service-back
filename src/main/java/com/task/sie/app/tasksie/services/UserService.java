package com.task.sie.app.tasksie.services;

import com.task.sie.app.tasksie.dto.user.UserDto;
import com.task.sie.app.tasksie.model.User;

import java.util.List;

public interface UserService {
    List<User> index();
    User create(UserDto userDto);
}
