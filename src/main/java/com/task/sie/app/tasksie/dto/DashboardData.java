package com.task.sie.app.tasksie.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class DashboardData {
    private String name;
    private  Long value;
}
