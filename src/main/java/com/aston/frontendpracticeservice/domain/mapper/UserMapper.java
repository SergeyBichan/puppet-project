package com.aston.frontendpracticeservice.domain.mapper;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

//    @Mapping(target = "roles", expression = "java(setDefaultRole())")
    @Mapping(target = "roles", source = "roles")
    UserDto toDto(User entity);

//    @Mapping(target = "roles", expression = "java(setDefaultRole())")
    User toUser(UserDto dto);

    default Set<Role> setDefaultRole() {
      return Set.of(Role.USER);
    }
}
