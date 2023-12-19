package com.webApp.User.user;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {


    User toEntity(UserDto userDto);

    UserDto toDto(User user);



    List<User> toEntity(List<UserDto> userDto);
    List<UserDto> toDto(List<User> user);
}


