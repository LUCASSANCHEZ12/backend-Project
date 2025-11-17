package com.backend.project.services;

import com.backend.project.controller.advice.exceptions.UserException;
import com.backend.project.controller.advice.exceptions.UserNotFoundException;
import com.backend.project.dto.user.UserRequest;
import com.backend.project.dto.user.UserDto;
import com.backend.project.dto.user.UserUpdateDTO;
import com.backend.project.entity.Role;
import com.backend.project.entity.User;
import com.backend.project.mapper.UserMapper;
import com.backend.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto register(UserRequest request) {

        if (repository.findByEmail(request.email()).isPresent()) {
            throw new UserException("Email is registered to another account.");
        }

        if (request.password().length() < 6) {
            throw new UserException("Password should be at least 6 characters");
        }
        if (request.name().isEmpty()) {
            throw new UserException("Name is required");
        }
        if (request.email().isEmpty()) {
            throw new UserException("Email is required");
        }

        var user = User.builder()
                .name(request.name())
                .password(request.password())
                .email(request.email())
                .role(Role.USER) // by default all users are USER role
                .build();
        return mapper.toDto(repository.save(user));
    }

    @Override
    public UserDto findById(Long id) {
        if(repository.findById(id).isEmpty()) {
            throw new UserException("User not found");
        }
        return mapper.toDto(repository.findById(id).get());
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = repository.findAll();
        return users.stream().map(mapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        if(repository.findById(id).isEmpty()) {
            throw new UserException("User not found");
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserUpdateDTO request) {
        User u = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        mapper.toEntity(request, u);
        return mapper.toDto(repository.save(u));
    }

}
