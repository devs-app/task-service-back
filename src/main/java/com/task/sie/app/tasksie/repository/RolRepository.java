package com.task.sie.app.tasksie.repository;

import com.task.sie.app.tasksie.enums.EnumRol;
import com.task.sie.app.tasksie.model.user.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByName(EnumRol name);
}