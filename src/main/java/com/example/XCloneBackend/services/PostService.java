package com.example.XCloneBackend.services;

import com.example.XCloneBackend.dto.PostDto;
import com.example.XCloneBackend.entities.LikeEntity;
import com.example.XCloneBackend.entities.PostEntity;
import com.example.XCloneBackend.entities.UserEntity;
import com.example.XCloneBackend.repositories.LikeRepository;
import com.example.XCloneBackend.repositories.PostRepository;
import com.example.XCloneBackend.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class PostService {
    @Autowired
    private  PostRepository postRepository;


    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ImageUpload imageUpload;

    @Autowired
    private UserRepository userRepository;

    public PostDto createPost(Long userId,String postDes, MultipartFile file){
        try{
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isPresent()){
            PostEntity newPost = new PostEntity();
            newPost.setPostDes(postDes);
            newPost.setUser(user.get());
            if(file!=null){
                String url = imageUpload.uploadImage(file);
                newPost.setPostImage(url);
            }
            newPost.setCreatedAt(LocalDateTime.now());
            return new PostDto(postRepository.save(newPost));
        }
        else {
            throw new Exception("User not found!");
        }
        }
        catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    public List<PostDto> getAllPostsForUserId(Long id){
        List<PostEntity> postEntities = postRepository.findAllForUser(id);
        List<PostDto> postDtos =new ArrayList<>();

        for(PostEntity post:postEntities){
            PostDto postDto = new PostDto(post);
            LikeEntity likeEntity = likeRepository.getByUserIdAndPostId(id,post.getId());
            postDto.setIsLiked(likeEntity != null);
            postDtos.add(postDto);
        }
        return postDtos;
    }

    public List<PostDto> getAllPostsForUserId2(Long id){
        List<PostEntity> postEntities = postRepository.findAllForUser(id);
        List<PostDto> postDtos =new ArrayList<>();
        Set<Long> likedPosts = new HashSet<>(likeRepository.getByUserId(id));

        for(PostEntity post:postEntities){
            PostDto postDto = new PostDto(post);
            postDto.setIsLiked(likedPosts.contains(post.getId()));
            postDtos.add(postDto);
        }
        return postDtos;
    }


    public List<PostDto> getAllPostsByUserId(Long userId){
        List<PostEntity> postEntities = postRepository.findAllByUserId(userId);
        List<PostDto> postDtos = postEntities.stream().map(p-> new PostDto(p)).toList();
        return postDtos;
    }


}
