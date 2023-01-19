package com.task.sie.app.tasksie.dto.rol;

import com.task.sie.app.tasksie.dto.BaseModelDto;
import com.task.sie.app.tasksie.dto.permission.PermissionDto;
import com.task.sie.app.tasksie.enums.EnumRol;
import com.task.sie.app.tasksie.enums.EnumStatus;
import liquibase.pro.packaged.B;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolDto extends BaseModelDto {
    private EnumRol name;
    private Set<PermissionDto> permissions;

    public RolDto() {

    }
    @Builder
    public RolDto(Long id, Long version, LocalDateTime createdAt, LocalDateTime updatedAt, EnumStatus status, EnumRol name, Set<PermissionDto> permissions) {
        super(id, version, createdAt, updatedAt, status);
        this.name = name;
        this.permissions = permissions;
    }
}
