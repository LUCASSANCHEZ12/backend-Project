package com.backend.project.dto.user;

public record UserDto(
        Long id,
        String name,
        String email,
        String role
) {
}