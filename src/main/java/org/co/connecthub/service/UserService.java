package org.co.connecthub.service;

import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.UserDto;

import java.util.List;

public interface UserService {
    //create
    UserDto createUser(UserDto userDto);
    //update
    UserDto updateUser(UserDto userDto,Integer userId);
    //getbyid
    UserDto getById(Integer userId);
    //get all
    List<UserDto> getAllUsers();
    //delete
    ApiResponse deleteUser(Integer userId);

}
