package com.datauser.mapper;

import com.datauser.user.model.User;
import com.datauser.user.payload.UserDto;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {

    }

    public static UserDto userToUserDto(User user) {
        return UserDto
                .builder()
                .name(user.getName())
                .surename(user.getSurename())
                .email(user.getEmail())
                .build();
    }

    public static User userDtoToUser(UserDto userDto) {
        return new User(userDto.getName(), userDto.getSurename(), userDto.getEmail());
    }

    public static List<UserDto> getUserDtoList(List<User> users) {
        if (users != null) {
            return users.stream().map(u ->
                    UserDto.builder()
                            .name(u.getName())
                            .surename(u.getSurename())
                            .email(u.getEmail()).build()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
