package com.task.sie.app.tasksie.repository;

import com.task.sie.app.tasksie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findOneByEmailOrUsername(String email,String username);
}
