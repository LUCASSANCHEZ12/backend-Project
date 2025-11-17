package com.backend.project.services;


import com.backend.project.dto.user.UserRequest;
import com.backend.project.dto.user.UserDto;
import com.backend.project.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserDto register(UserRequest request);
    UserDto findById(Long id);
    List<UserDto> getAll();
    void deleteById(Long id);
    UserDto update(Long id, UserUpdateDTO request);
}
