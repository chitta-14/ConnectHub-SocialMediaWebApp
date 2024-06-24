package org.co.connecthub.controller;

import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.UserDto;
import org.co.connecthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping("/")
    public ResponseEntity<UserDto>createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<UserDto>(this.userService.createUser(userDto), HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto,@PathVariable("userId")Integer userId){
        return  new ResponseEntity<UserDto>(this.userService.updateUser(userDto,userId),HttpStatus.OK );
    }
    //get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getUser(@PathVariable Integer userId){
        return new ResponseEntity<UserDto>(this.userService.getById(userId),HttpStatus.OK);
    }
    //get list of user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUsers(){
        return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(),HttpStatus.OK);
    }
    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable Integer userId){
        return new ResponseEntity<ApiResponse>(this.userService.deleteUser(userId),HttpStatus.OK);
    }

}
