package com.example.XCloneBackend.controllers;

import com.example.XCloneBackend.entities.LikeEntity;
import com.example.XCloneBackend.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<String> setLikeEntity(@PathVariable Long userId,@PathVariable Long postId){
        LikeEntity likeEntity = likeService.setLikeEntity(userId,postId);
       return  ResponseEntity.ok("Like successfully!");
    }

    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<String> disLike(@PathVariable Long userId,@PathVariable Long postId){
        likeService.disLike(userId,postId);
        return  ResponseEntity.ok("Dislike successfully!");
    }
}
