package com.task.sie.app.tasksie.dto;

import com.task.sie.app.tasksie.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseModelDto {
    private Long id;
    private Long version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private EnumStatus status;
}
