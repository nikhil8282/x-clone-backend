package com.example.XCloneBackend.controllers;

import com.example.XCloneBackend.dto.LoginRequestDto;
import com.example.XCloneBackend.dto.LoginResponseDto;
import com.example.XCloneBackend.dto.SignUpRequestDto;
import com.example.XCloneBackend.repositories.UserRepository;
import com.example.XCloneBackend.security.JwtService;
import com.example.XCloneBackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private UserRepository userRepository;

    public AuthController(AuthService authService, AuthenticationManager manager, UserDetailsService userDetailsService) {
        this.authService = authService;
        this.manager = manager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody SignUpRequestDto signUpRequestDto){
    try{
        authService.signUpUser(signUpRequestDto);
    }
    catch (Exception e){
        throw  new RuntimeException(e.getMessage());
    }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){
        try{

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
            performAuthenticate(loginRequestDto.getEmail(),loginRequestDto.getPassword());
            String token = jwtService.generateToken(userDetails);
            LoginResponseDto response = new LoginResponseDto(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    private void performAuthenticate(String email,String password){
        try{
            manager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }
        catch (BadCredentialsException e){
    throw new RuntimeException("Invalid email or password");
        }
    }






}
