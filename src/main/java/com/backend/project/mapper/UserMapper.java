package com.backend.project.mapper;

import com.backend.project.dto.user.UserDto;
import com.backend.project.dto.user.UserUpdateDTO;
import com.backend.project.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserDto toDto(User entity);

    void toEntity(UserUpdateDTO dto, @MappingTarget User entity);
}
