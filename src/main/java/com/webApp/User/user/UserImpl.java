package com.webApp.User.user;


import com.webApp.User.logs.Log;
import com.webApp.User.logs.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserImpl implements UserService {


    @Autowired
    private  UserRepository userRepository;


    @Autowired
    private    UserMapper userMapper;




    @Override
    @org.springframework.transaction.annotation.Transactional
    public void save(UserDto userDto) {

        User user=userMapper.toEntity(userDto);
        userRepository.save(user);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public UserDto update(UserDto userDto) throws Exception {

      User existingUser=userRepository.findById(userDto.getId())
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setGender(userDto.getGender());
        existingUser.setGmail(userDto.getGmail());
        existingUser.setUserName(userDto.getUserName());
        existingUser.setPassword(userDto.getPassword());


        User updateUser=userRepository.save(existingUser);
        return userMapper.toDto(updateUser);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDto getById(Long id) throws Exception {

        Optional<User>user=userRepository.findById(id);

        if(user.isPresent()){

            return userMapper.toDto(user.get());
        }else
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public void delete(Long id) {

        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }






}
