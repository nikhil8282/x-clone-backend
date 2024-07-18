package com.example.XCloneBackend.dto;


import com.example.XCloneBackend.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchedUserDto extends UserDto{
    private Boolean isFollowed;
    public SearchedUserDto(UserEntity user,Boolean isFollowed){
        super(user);
        this.isFollowed=isFollowed;
    }
    public SearchedUserDto(){}
}
