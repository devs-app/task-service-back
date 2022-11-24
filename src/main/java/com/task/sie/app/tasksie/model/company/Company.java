package com.task.sie.app.tasksie.model.company;

import com.task.sie.app.tasksie.model.BaseModel;
import com.task.sie.app.tasksie.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "companies")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company extends BaseModel {
    private String name;
    private String commercialName;
    private String document;
    private String phone;
    private String mobile;
    private String logo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "legal_representative_id")
    private LegalRepresentative legalRepresentative;
    @ManyToMany
    @JoinTable(name = "user_company", joinColumns= @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
