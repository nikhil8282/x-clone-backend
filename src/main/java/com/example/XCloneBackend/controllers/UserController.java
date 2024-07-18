package com.example.XCloneBackend.controllers;

import com.example.XCloneBackend.dto.SearchedUserDto;
import com.example.XCloneBackend.dto.UserDto;
import com.example.XCloneBackend.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> fetchUser(HttpServletRequest request){
        try{
            UserDto user= userService.getUserByUserEmail(request.getAttribute("userEmail").toString());
            return ResponseEntity.ok(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @GetMapping("/{userId}/search/{searchId}")
    public ResponseEntity<?> getSearchedUser(@PathVariable Long userId,@PathVariable Long searchId){
        try{
            SearchedUserDto user= userService.getUserById(userId,searchId);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<UserDto> getAllUsersByName(@RequestParam(name = "user_name") String userName, @RequestParam(name = "full_name") String fullName){
        return  userService.getUserByUserNameAndFullName(userName,fullName);
    }


    @PostMapping("/update")
    public ResponseEntity<?> updateUser(HttpServletRequest request,@RequestParam(value = "coverImage",required = false) MultipartFile coverImage, @RequestParam(value = "profileImage",required = false) MultipartFile profileImage,@RequestParam("fullName") String fullName,@RequestParam("userName") String userName){
       try{
           UserDto user = userService.updateUser(coverImage,profileImage,fullName,userName,request.getAttribute("userEmail").toString());
           return ResponseEntity.ok(user);
       }
       catch (Exception e){
           throw  new RuntimeException(e.getMessage());
       }

    }

    @GetMapping("/{id}/suggested_users")
    public ResponseEntity<?> getSuggestedUser(@PathVariable Long id){
            return ResponseEntity.ok(userService.getSuggestedUsers(id));
    }


}
