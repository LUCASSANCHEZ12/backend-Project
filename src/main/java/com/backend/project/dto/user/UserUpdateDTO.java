package com.backend.project.dto.user;

import com.backend.project.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(
        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        Role role
) {

}
