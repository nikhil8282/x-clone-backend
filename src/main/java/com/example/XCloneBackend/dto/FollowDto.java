package com.example.XCloneBackend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowDto {
    private List<UserDto> followers;
    private List<UserDto> follows;

}
