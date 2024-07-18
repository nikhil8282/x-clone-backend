package com.example.XCloneBackend.services;

import com.example.XCloneBackend.dto.SearchedUserDto;
import com.example.XCloneBackend.dto.SuggestedUserDto;
import com.example.XCloneBackend.dto.UserDto;
import com.example.XCloneBackend.entities.FollowEntity;
import com.example.XCloneBackend.entities.UserEntity;
import com.example.XCloneBackend.repositories.FollowRepository;
import com.example.XCloneBackend.repositories.PostRepository;
import com.example.XCloneBackend.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService  implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final FollowRepository followRepository;

    @Autowired
    private ImageUpload imageUpload;


    public UserService(PostService postService, PostRepository postRepository, UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }


    public SearchedUserDto getUserById(Long userId,Long searchedId){
        try {
            Optional<UserEntity> searchedUser = userRepository.findById(searchedId);
            if(searchedUser.isPresent()){
                Optional<FollowEntity> follow = followRepository.getByFollowedByAndFollowedTo(userId,searchedId);
                return new SearchedUserDto(searchedUser.get(),follow.isPresent());
            }
            else {
                throw new RuntimeException("User Not found");
            }

        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public UserDto getUserByUserEmail(String email){
        try{
            UserEntity user = userRepository.getByEmail(email).get();
            return new UserDto(user);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.getByEmail(userName).get();
    }

    public List<UserDto> getUserByUserNameAndFullName(String userName,String fullName) throws UsernameNotFoundException {
        return userRepository.getUserByUserNameAndFullName(userName,fullName).stream().map(u->new UserDto(u)).toList();
    }

    public UserDto updateUser(MultipartFile coverImage,MultipartFile profileImage,String fullName,String userName,String email){

        try{
            UserEntity user = userRepository.getByEmail(email).get();
            if(!userName.isEmpty())user.setUserName(userName);
            if(!fullName.isEmpty())user.setFullName(fullName);
            if(!profileImage.isEmpty()){
                    String url = imageUpload.uploadImage(profileImage);
                    user.setProfileImage(url);
            }
            if(!coverImage.isEmpty()){
                String url = imageUpload.uploadImage(coverImage);
                user.setCoverImage(url);
            }
            return new UserDto(userRepository.save(user));

        }
        catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

    public List<SuggestedUserDto> getSuggestedUsers(Long id){
        return userRepository.getSuggestedUsers(id).stream().map(SuggestedUserDto::new).toList();
    }

}
