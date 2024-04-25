package com.example.rest_apis.controller;

import com.example.rest_apis.exceptions.ApiExceptionHandler;
import com.example.rest_apis.payloads.JwtAuthenticationRequest;
import com.example.rest_apis.payloads.JwtAuthenticationResponse;
import com.example.rest_apis.payloads.UserDto;
import com.example.rest_apis.security.JwtTokenHelper;
import com.example.rest_apis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth/")
public class AuthRestController {


    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")//url is /api/auth/login
    public ResponseEntity<JwtAuthenticationResponse> createToken(@RequestBody JwtAuthenticationRequest request) throws Exception{

        //authenticate method body define below
        this.authenticate(request.getUsername(), request.getPassword());
        //if authentication going well, then we need to generate our token.

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        String ourGeneratedToken = this.jwtTokenHelper.generateToken(userDetails);//generateToken takes userDetails
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(ourGeneratedToken);

        //return
        return new ResponseEntity<JwtAuthenticationResponse>(response, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {

        //need to authenticate
        //authenticationManager can authenticate

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println(usernamePasswordAuthenticationToken+"Wasim...");
        //authenticate the username and password


        try {

            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Username or Password!");
            throw new ApiExceptionHandler("Invalid Username or Password!");
        }
        //if authentication going well, then we need to generate our token.
        //if not going well, then it will generate exception... we will handle it globally



    }

    //register new user API
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto) throws InterruptedException {
        UserDto registeredNewUser = this.userService.registerNewUser(userDto);

        return new ResponseEntity<UserDto>(registeredNewUser, HttpStatus.CREATED);

    }
}
