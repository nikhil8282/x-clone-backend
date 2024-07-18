package com.example.XCloneBackend.dto;


import com.example.XCloneBackend.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto extends CommonUserDto{
    private Long postId;
    private  String postImage;
    private  String postDes;
    private  LocalDateTime createdAt;
    private  Long likeCount;
    private  Boolean isLiked;
    public PostDto(PostEntity post) {
        super(post.getUser());
        postId=post.getId();
        postImage=post.getPostImage();
        postDes=post.getPostDes();
        createdAt=post.getCreatedAt();
        likeCount=post.getLikeCount();

    }
}
