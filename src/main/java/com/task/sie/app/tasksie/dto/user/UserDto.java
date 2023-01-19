package com.task.sie.app.tasksie.dto.user;

import com.task.sie.app.tasksie.dto.BaseModelDto;
import com.task.sie.app.tasksie.dto.rol.RolDto;
import com.task.sie.app.tasksie.enums.EnumStatus;
import lombok.*;

import java.time.LocalDateTime;
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

    public UserDto() {
    }
    @Builder
    public UserDto(Long id, Long version, LocalDateTime createdAt, LocalDateTime updatedAt, EnumStatus status, String username, String password, String email, String avatar, Boolean changePassword, Set<RolDto> roles) {
        super(id, version, createdAt, updatedAt, status);
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.changePassword = changePassword;
        this.roles = roles;
    }
}
