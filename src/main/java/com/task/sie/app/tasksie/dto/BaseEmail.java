package com.task.sie.app.tasksie.dto;

import lombok.*;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BaseEmail {
    private String subject;
    private String to;
    private String template;
    private HashMap< String, Object > params;
}
