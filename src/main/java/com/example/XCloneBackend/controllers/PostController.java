package com.example.XCloneBackend.controllers;


import com.example.XCloneBackend.dto.PostDto;
import com.example.XCloneBackend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public PostDto createPost(@RequestParam(value = "image",required = false)MultipartFile file,@RequestParam("postDes") String postDes,@RequestParam("userId") Long userId){
        try{
            return postService.createPost(userId,postDes,file);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public List<PostDto> getPostForUserId(@PathVariable Long userId){
    return postService.getAllPostsForUserId(userId);
    }


    @GetMapping("/user/{userId}")
    public List<PostDto> getPostsByUserId(@PathVariable Long userId){
    return postService.getAllPostsByUserId(userId);
    }


}
