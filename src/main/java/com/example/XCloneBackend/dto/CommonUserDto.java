package com.example.XCloneBackend.dto;

import com.example.XCloneBackend.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonUserDto {
    private Long userId;
    private String fullName;
    private String userName;
    private String profileImage;

    public CommonUserDto(){}
    public CommonUserDto(UserEntity user){
        this.userId=user.getId();
        this.fullName=user.getFullName();
        this.userName=user.getUserName();
        this.profileImage=user.getProfileImage();
    }
}
