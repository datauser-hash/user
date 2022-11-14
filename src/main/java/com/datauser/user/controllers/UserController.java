package com.datauser.user.controllers;

import com.datauser.user.payload.UserDto;
import com.datauser.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserDto> userDtoList = this.userService.getAll();
        if (userDtoList == null || userDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }


    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        UserDto result = this.userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
