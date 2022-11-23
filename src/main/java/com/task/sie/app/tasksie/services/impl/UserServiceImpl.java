package com.task.sie.app.tasksie.services.impl;

import com.task.sie.app.tasksie.dto.ResponseError;
import com.task.sie.app.tasksie.dto.TokenDto;
import com.task.sie.app.tasksie.dto.user.UserConverter;
import com.task.sie.app.tasksie.dto.user.UserDto;
import com.task.sie.app.tasksie.enums.EnumStatus;
import com.task.sie.app.tasksie.model.User;
import com.task.sie.app.tasksie.repository.UserRepository;
import com.task.sie.app.tasksie.security.AuthCredential;
import com.task.sie.app.tasksie.security.TokenUtils;
import com.task.sie.app.tasksie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> index() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto userDto) {
        User u = UserConverter.toModel(userDto);
        return userRepository.save(u);
    }

    @Override
    public ResponseEntity login(AuthCredential presenter) throws ResponseError {
        Optional<User> user = userRepository
                .findOneByEmailOrUsername(presenter.getUsername(), presenter.getUsername());

        if(user.isEmpty())
            throw new ResponseError("Credenciales incorrectas");

        if(user.get().getStatus().equals(EnumStatus.INA))
            throw new ResponseError("Uruario Bloqueado");

        if(passwordEncoder.matches(presenter.getPassword(), user.get().getPassword())){
            UserDto userDto = UserConverter.toDto(user.get());
            userDto.setPassword("");
            return new ResponseEntity(new TokenDto(TokenUtils.generateToken(user.get().getUsername(), user.get().getEmail()), userDto), HttpStatus.OK);
        }else {
            throw new ResponseError("Credenciales incorrectas");
        }
    }
}
