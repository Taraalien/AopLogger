package com.webApp.User.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private  UserService userService;


    @PostMapping("/save")
    public void save(@RequestBody @Validated UserDto userDto)
    {
        userService.save(userDto);

    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid UserDto userDto) throws Exception {
        UserDto userDto1= userService.update(userDto);
        return ResponseEntity.ok().body(userDto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getById(@PathVariable Long id)throws Exception{

        UserDto userDto1=userService.getById(id);
        return ResponseEntity.ok(userDto1);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        userService.delete(id);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAll(){

        List<User> users=userService.getAll();
        return ResponseEntity.ok(users);
    }


}
