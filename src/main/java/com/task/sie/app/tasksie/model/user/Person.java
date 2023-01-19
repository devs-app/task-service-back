package com.task.sie.app.tasksie.model.user;

import com.task.sie.app.tasksie.model.BaseModel;
import lombok.*;

import javax.persistence.*;

@Table(name = "persons")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseModel {
    private String name;
    private String lastName;
    private String document;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
