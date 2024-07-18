package com.example.XCloneBackend.services;


import com.example.XCloneBackend.dto.SignUpRequestDto;
import com.example.XCloneBackend.entities.UserEntity;
import com.example.XCloneBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service

public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    public void signUpUser(SignUpRequestDto signUpRequestDto){
        try {
        Optional<UserEntity> userEntity = userRepository.getByEmail(signUpRequestDto.getEmail());
        if(userEntity.isPresent()){throw new RuntimeException("Email is already registered!");}
        UserEntity newUserEntity = new UserEntity(signUpRequestDto);
        newUserEntity.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        userRepository.save(newUserEntity);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }



}
