package com.task.sie.app.tasksie.dto.user;

import com.task.sie.app.tasksie.dto.BaseModelDto;
import com.task.sie.app.tasksie.dto.rol.RolDto;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseModelDto {
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Boolean changePassword;
    private Set<RolDto> roles;
}
