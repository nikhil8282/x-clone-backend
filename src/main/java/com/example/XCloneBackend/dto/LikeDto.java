package com.example.XCloneBackend.dto;

import java.util.List;

public class LikeDto {
    private List<UserDto> users;
    public LikeDto(List<UserDto> users){
        this.users=users;
    }
}

