package com.task.sie.app.tasksie.dto.user;

import com.task.sie.app.tasksie.dto.rol.RolConverter;
import com.task.sie.app.tasksie.model.User;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class UserConverter {
    public static UserDto toDto(User entity) {
    ModelMapper modelMapper = new ModelMapper();
        UserDto presenter = new UserDto();
    modelMapper.map(entity, presenter);
    presenter.setPassword("xxxxxxxxxxxxxxxxxxxx");
    presenter.setRoles(entity.getRoles()
            .stream().map(RolConverter::toDto).collect(Collectors.toSet()));
    return presenter;
    }

    public static User toModel(UserDto userDto){
        ModelMapper modelMapper = new ModelMapper();
        User user = new User();
        modelMapper.map(userDto, user);
        return user;
    }

}
