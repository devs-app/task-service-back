package com.task.sie.app.tasksie.model.user;

import com.task.sie.app.tasksie.enums.EnumStatus;
import com.task.sie.app.tasksie.model.BaseModel;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "sec_users")
@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseModel {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String avatar;
    private Boolean changePassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sec_user_rol", joinColumns= @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;
    @Builder
    public User(Long id, Long version, LocalDateTime createdAt, LocalDateTime updatedAt, EnumStatus status, String username, String password, String email, String avatar, Boolean changePassword, Set<Rol> roles) {
        super(id, version, createdAt, updatedAt, status);
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.changePassword = changePassword;
        this.roles = roles;
    }
}
