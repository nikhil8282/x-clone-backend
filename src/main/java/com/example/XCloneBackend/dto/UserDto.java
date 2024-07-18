package com.example.XCloneBackend.dto;

import com.example.XCloneBackend.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends CommonUserDto {
    private String email;
    private String coverImage;
    private Long followersCount;
    private Long followingCount;

    public UserDto(UserEntity user){
        super(user);
        this.email = user.getEmail();
        this.coverImage = user.getCoverImage();
        this.followersCount=user.getFollowers();
        this.followingCount=user.getFollowing();
    }

}
