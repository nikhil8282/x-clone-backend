package com.example.XCloneBackend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private  String postImage;
    private  String postDes;
    private LocalDateTime createdAt;
    private  Long userId;

}
