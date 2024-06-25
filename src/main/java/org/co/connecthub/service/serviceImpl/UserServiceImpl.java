package org.co.connecthub.service.serviceImpl;

import org.co.connecthub.entity.Role;
import org.co.connecthub.entity.User;
import org.co.connecthub.exception.ResourceNotFoundException;
import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.AppConstants;
import org.co.connecthub.payload.UserDto;
import org.co.connecthub.repository.RoleRepository;
import org.co.connecthub.repository.UserRepository;
import org.co.connecthub.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);
        Role role=this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User createdUser=this.userRepository.save(user);
        return this.modelMapper.map(createdUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User existingUser=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));
        User user=this.modelMapper.map(userDto,User.class);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setAbout(user.getAbout());
        this.userRepository.save(existingUser);
        return this.modelMapper.map(existingUser,UserDto.class);
    }

    @Override
    public UserDto getById(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList=this.userRepository.findAll();
        List<UserDto> userDtoList=userList.stream().map((e)->this.modelMapper.map(e,UserDto.class)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public ApiResponse deleteUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        this.userRepository.delete(user);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("User deleted sucessfully...");
        apiResponse.setResult(true);
        return apiResponse;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        Role role=this.roleRepository.findById(AppConstants.ADMIN_USER).get();
        user.getRoles().add(role);
        User newUser=this.userRepository.save(user);
        return this.modelMapper.map(newUser,UserDto.class);
    }
}
