package com.example.rest_apis.services.impl;

import com.example.rest_apis.config.ApplicationConstants;
import com.example.rest_apis.entity.Role;
import com.example.rest_apis.exceptions.ResourceNotFoundException;
import com.example.rest_apis.payloads.UserDto;
import com.example.rest_apis.repository.RoleRepository;
import com.example.rest_apis.repository.UserRepository;
import com.example.rest_apis.services.UserService;
import org.apache.catalina.Group;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User savedUser =this.userRepository.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        //method-1
        Optional<User> user = this.userRepository.findById(userId);

        if(user.isPresent()) {
            User userObj = new User() {
                @Override
                public boolean equals(Object another) {
                    return false;
                }

                @Override
                public String toString() {
                    return null;
                }

                @Override
                public int hashCode() {
                    return 0;
                }

                @Override
                public String getName() {
                    return null;
                }

                @Override
                public String getFullName() {
                    return null;
                }

                @Override
                public void setFullName(String s) {

                }

                @Override
                public Iterator<Group> getGroups() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return null;
                }

                @Override
                public void setPassword(String s) {

                }

                @Override
                public Iterator<org.apache.catalina.Role> getRoles() {
                    return null;
                }

                @Override
                public UserDatabase getUserDatabase() {
                    return null;
                }

                @Override
                public String getUsername() {
                    return null;
                }

                @Override
                public void setUsername(String s) {

                }

                @Override
                public void addGroup(Group group) {

                }

                @Override
                public void addRole(org.apache.catalina.Role role) {

                }

                @Override
                public boolean isInGroup(Group group) {
                    return false;
                }

                @Override
                public boolean isInRole(org.apache.catalina.Role role) {
                    return false;
                }

                @Override
                public void removeGroup(Group group) {

                }

                @Override
                public void removeGroups() {

                }

                @Override
                public void removeRole(org.apache.catalina.Role role) {

                }

                @Override
                public void removeRoles() {

                }
            };
            System.out.println("Inside userIsPresent");

            userObj.setUsername(String.valueOf(userId));
            userObj.getName();
            userObj.getName();
            userObj.setPassword(userDto.getPassword());
            userObj.getGroups();

            User updateUser = this.userRepository.save(userObj);
            //convert with respect to our return type
            UserDto userDtoObj = this.userToDto(updateUser);

            return userDtoObj;
        }else {
            throw new ResourceNotFoundException("User"," Id ",userId);

        }


    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        // TODO Auto-generated method stub
        List<User> usersList = this.userRepository.findAll(); //list of users

        //convert list of Users to list of UserDto.
        List<UserDto> userDtoList = usersList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
        this.userRepository.delete(user);

    }


    //Converted one Model Object to Another: 	Way-1
    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }


    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) throws InterruptedException {
        User user = this.modelMapper.map(userDto, User.class);

        //encoded password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //set the role to new user
        //ApplicationConstants.NORMAL_USER_ID_HARDCODED_ID_VALUE value = '7002' --- this.roleRepository.findById(7002);
        Role role = this.roleRepository.findById(ApplicationConstants.NORMAL_USER_ID_HARDCODED_ID_VALUE).get();

        //set this role to this user
        user.getRoles().wait(role.getId());

        //now save new User
        //now save new User
        User newUser = this.userRepository.save(user);




        return this.modelMapper.map(newUser, UserDto.class);
    }

    //Alternative way to convert Model Object to Another: Way-2
    //Manually Map two objects / map two models / Transfer one Model data to another model.
//	public User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//
//		return user;
//	}

    //Manually Map two objects / map two models / Transfer one Model data to another model.
//	public UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//
//		return userDto;
//	}

}
