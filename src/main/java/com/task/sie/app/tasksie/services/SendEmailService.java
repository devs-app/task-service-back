package com.task.sie.app.tasksie.services;

import com.task.sie.app.tasksie.dto.BaseEmail;

public interface SendEmailService {
    void sentEmailActivateUser(BaseEmail baseEmail);
}
