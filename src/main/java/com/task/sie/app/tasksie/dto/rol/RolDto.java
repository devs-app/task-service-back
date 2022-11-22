package com.task.sie.app.tasksie.dto.rol;

import com.task.sie.app.tasksie.dto.BaseModelDto;
import com.task.sie.app.tasksie.dto.permission.PermissionDto;
import com.task.sie.app.tasksie.enums.EnumRol;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolDto extends BaseModelDto {
    private EnumRol name;
    private Set<PermissionDto> permissions;
}
