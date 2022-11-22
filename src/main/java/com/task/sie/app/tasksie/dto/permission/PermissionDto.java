package com.task.sie.app.tasksie.dto.permission;

import com.task.sie.app.tasksie.dto.BaseModelDto;
import com.task.sie.app.tasksie.enums.EnumStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionDto extends BaseModelDto {
    private String name;
    private EnumStatus status;
}
