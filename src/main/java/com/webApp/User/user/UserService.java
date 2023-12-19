package com.webApp.User.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(UserDto userDto);
    UserDto update( UserDto userDto) throws Exception;
    UserDto getById(Long id) throws Exception;
    void delete(Long id);
    List<User> getAll();


}
