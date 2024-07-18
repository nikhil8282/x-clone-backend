package com.example.XCloneBackend.controllers;

import com.example.XCloneBackend.dto.CommonUserDto;
import com.example.XCloneBackend.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class FollowController {

    @Autowired
    private  final FollowService followService;

    public  FollowController(FollowService followService){
        this.followService = followService;
    }


    @PostMapping("/follow/{followedBy}/{followedTo}")
    public void follow(@PathVariable Long followedBy,@PathVariable Long followedTo){
        followService.setFollow(followedBy,followedTo);
    }

    @DeleteMapping("/unfollow/{followedBy}/{followedTo}")
    public void unfollow(@PathVariable Long followedBy,@PathVariable Long followedTo){
        followService.setUnFollow(followedBy,followedTo);
    }


    @GetMapping("/followers/{id}")
    public ResponseEntity<?> getFollowers(@PathVariable Long id){
        List<CommonUserDto> followers = followService.getFollowers(id);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/followings/{id}")
    public ResponseEntity<?> getFollowings(@PathVariable Long id){
        List<CommonUserDto> followings = followService.getFollowings(id);
        if(followings.isEmpty())return ResponseEntity.internalServerError().body("Server error");
        return ResponseEntity.ok(followings);
    }

}
