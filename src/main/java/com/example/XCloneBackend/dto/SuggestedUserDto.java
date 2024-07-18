package com.example.XCloneBackend.dto;

import com.example.XCloneBackend.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestedUserDto extends CommonUserDto{
    private Boolean isFollowed=false;
    public SuggestedUserDto(UserEntity user){
        super(user);
    }
    public SuggestedUserDto(){}
}
