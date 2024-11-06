package com.aston.frontendpracticeservice.domain.mapper;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.security.Role;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "roles", expression = "java(setDefaultRole())")
    UserDto toDto(User entity);

//    @Mapping(target = "roles", expression = "java(setDefaultRole())")
    User toUser(UserDto dto);

    default Set<Role> setDefaultRole() {
      return Set.of(Role.USER);
    }
}
