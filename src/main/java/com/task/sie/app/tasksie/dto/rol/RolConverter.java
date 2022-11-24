package com.task.sie.app.tasksie.dto.rol;

import com.task.sie.app.tasksie.dto.permission.PermissionConverter;
import com.task.sie.app.tasksie.model.user.Rol;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class RolConverter {
    public static Rol toModel(RolDto rolDto) {
        ModelMapper modelMapper = new ModelMapper();
        Rol rol = new Rol();
        modelMapper.map(rolDto, rol);
        rol.setPermissions(rolDto.getPermissions()
                .stream().map(PermissionConverter::toModel).collect(Collectors.toSet()));
        return rol;
    }

    public static RolDto toDto(Rol entity) {
        ModelMapper modelMapper = new ModelMapper();
        RolDto rolDto = new RolDto();
        modelMapper.map(entity, rolDto);
        rolDto.setPermissions(entity.getPermissions()
                .stream().map(PermissionConverter::toDto).collect(Collectors.toSet()));
        return rolDto;
    }
}
