package com.task.sie.app.tasksie.model.company;

import com.task.sie.app.tasksie.model.BaseModel;
import com.task.sie.app.tasksie.model.user.Person;
import lombok.*;

import javax.persistence.*;

@Table(name = "legal_representative")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegalRepresentative extends BaseModel {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;
}
