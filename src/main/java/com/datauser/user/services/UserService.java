package com.datauser.user.services;

import com.datauser.mapper.UserMapper;
import com.datauser.user.model.User;
import com.datauser.user.payload.UserDto;
import com.datauser.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    
    private static final String REQUEST_IS_NULL = "nullRequest";
    

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserDto> getAll() {
        List<User> users = this.userRepository.findAll();
        return UserMapper.getUserDtoList(users);
    }

    public UserDto create(UserDto userDto) {
        User savedUser = this.userRepository.save(UserMapper.userDtoToUser(userDto));
        return UserMapper.userToUserDto(savedUser);
    }
    

}
