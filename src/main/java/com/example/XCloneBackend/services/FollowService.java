package com.example.XCloneBackend.services;
import com.example.XCloneBackend.dto.CommonUserDto;
import com.example.XCloneBackend.entities.FollowEntity;
import com.example.XCloneBackend.entities.UserEntity;
import com.example.XCloneBackend.repositories.FollowRepository;
import com.example.XCloneBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    public void setFollow(Long followedById,Long followedToId){
        FollowEntity followEntity = new FollowEntity();
        UserEntity followedBy = userRepository.findById(followedById).get();
        UserEntity followedTo = userRepository.findById(followedToId).get();

        followedBy.setFollowing(followedBy.getFollowing()+1);
        followedTo.setFollowers(followedTo.getFollowers()+1);

        followEntity.setFollowedBy(followedBy);
        followEntity.setFollowedTo(followedTo);
        followRepository.save(followEntity);
    }

    public void setUnFollow(Long followedById,Long followedToId){
        userRepository.decraseFollower(followedToId);
        userRepository.decraseFollowing(followedById);
        Optional<FollowEntity> followEntity = followRepository.getByFollowedByAndFollowedTo(followedById,followedToId);
        followRepository.deleteById(followEntity.get().getId());
    }

    public List<CommonUserDto> getFollowers(Long id){
        List<UserEntity> followers = followRepository.getFollowers(id);
        return followers.stream().map(CommonUserDto::new).toList();
    }
    public List<CommonUserDto> getFollowings(Long id){
        List<UserEntity> followings = followRepository.getFollows(id);
        return followings.stream().map(CommonUserDto::new).toList();
    }
}
