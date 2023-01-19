package com.task.sie.app.tasksie.dto.company;

import com.task.sie.app.tasksie.dto.user.PersonDto;
import com.task.sie.app.tasksie.dto.user.UserDto;
import lombok.Data;

@Data
public class LegalRepresentativeDto {
    private PersonDto person;

    private String email;
}
