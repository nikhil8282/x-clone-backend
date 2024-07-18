package com.example.XCloneBackend.services;

import com.example.XCloneBackend.dto.CommentDto;
import com.example.XCloneBackend.dto.CommentRequestDto;
import com.example.XCloneBackend.entities.CommentEntity;
import com.example.XCloneBackend.entities.UserEntity;
import com.example.XCloneBackend.repositories.CommentRepository;
import com.example.XCloneBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CommentDto> getComments(Long id){
        List<CommentEntity> comments =  commentRepository.getByPostId(id);
        System.out.println(comments.size());
        return comments.stream().map(comment->new CommentDto(comment)).toList();
    }

    public CommentDto setComment(CommentRequestDto commentRequestDto){
        CommentEntity commentEntity = new CommentEntity(commentRequestDto);
        commentEntity.setComment(commentRequestDto.getComment());
        commentEntity.setPostId(commentRequestDto.getPostId());
        UserEntity user = userRepository.findById(commentRequestDto.getUserId()).get();
        commentEntity.setUser(user);
        commentEntity.setCreatedAt(LocalDateTime.now());
        return new CommentDto(commentRepository.save(commentEntity));
    }
}
