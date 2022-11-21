package com.task.sie.app.tasksie.services.impl;

import com.task.sie.app.tasksie.dto.user.UserConverter;
import com.task.sie.app.tasksie.dto.user.UserDto;
import com.task.sie.app.tasksie.model.User;
import com.task.sie.app.tasksie.repository.UserRepository;
import com.task.sie.app.tasksie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> index() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto userDto) {
        User u = UserConverter.toModel(userDto);
        return userRepository.save(u);
    }
}
