package com.example.XCloneBackend.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private String comment;
    private Long userId;
    private  Long postId;
}
