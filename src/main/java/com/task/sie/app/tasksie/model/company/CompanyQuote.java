package com.task.sie.app.tasksie.model.company;

import com.task.sie.app.tasksie.model.BaseModel;
import com.task.sie.app.tasksie.model.user.Rol;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "company_quote")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyQuote extends BaseModel {
    @OneToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne()
    @JoinColumn(name = "rol_id")
    private Rol rol;

    private BigDecimal quote;

}
