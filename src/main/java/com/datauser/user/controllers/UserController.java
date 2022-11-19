package com.datauser.user.controllers;

import com.datauser.user.payload.OperationMessage;
import com.datauser.user.payload.UserDto;
import com.datauser.user.services.UserService;
import com.datauser.user.services.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final ValidationService validationService;

    @Autowired
    public UserController(UserService userService, ValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        log.info(">> UserController.getAll enter");
        List<UserDto> userDtoList = this.userService.getAll();
        if (userDtoList == null || userDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        log.info("<< UserController.getAll exit");
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        log.info(">> UserController.create enter");
        String validationKey = this.validationService.validate(userDto);
        if (!validationKey.equals("")) {
            log.warn("<< UserController.create exit FAIL");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationMessage(validationKey));
        }
        UserDto result = this.userService.create(userDto);
        log.info("<< UserController.create exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
