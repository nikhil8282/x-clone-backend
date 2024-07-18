package com.example.XCloneBackend.dto;
import com.example.XCloneBackend.entities.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto extends CommonUserDto{
    private String comment;
    private Long postId;
    private LocalDateTime createdAt;
    public CommentDto(CommentEntity commentEntity){
        super(commentEntity.getUser());
        this.comment=commentEntity.getComment();
        this.createdAt=commentEntity.getCreatedAt();
        this.postId=commentEntity.getPostId();
    }

}
