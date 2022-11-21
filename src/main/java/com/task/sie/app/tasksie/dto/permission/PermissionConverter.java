package com.task.sie.app.tasksie.dto.permission;

import com.task.sie.app.tasksie.model.Permission;
import org.modelmapper.ModelMapper;

public class PermissionConverter {
    public static Permission toModel(PermissionDto permissionDto) {
        ModelMapper modelMapper = new ModelMapper();
        Permission permission= new Permission();
        modelMapper.map(permissionDto, permission);
        return permission;
    }
    public static PermissionDto toDto(Permission entity) {
        ModelMapper modelMapper = new ModelMapper();
        PermissionDto presenter = new PermissionDto();
        modelMapper.map(entity, presenter);
        return presenter;
    }
}
