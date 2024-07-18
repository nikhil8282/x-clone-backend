package com.example.XCloneBackend.controllers;

import com.example.XCloneBackend.dto.CommentDto;
import com.example.XCloneBackend.dto.CommentRequestDto;
import com.example.XCloneBackend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
   @Autowired
   private CommentService commentService;

   @PostMapping("comment/")
   public CommentDto setComment(@RequestBody CommentRequestDto commentRequestDto){
          return commentService.setComment(commentRequestDto);
   }

   @GetMapping("comments/{id}")
   public List<CommentDto> getAllComments(@PathVariable Long id){
      return commentService.getComments(id);
   }

   }
