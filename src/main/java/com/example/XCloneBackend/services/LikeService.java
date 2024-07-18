package com.example.XCloneBackend.services;
import com.example.XCloneBackend.entities.LikeEntity;
import com.example.XCloneBackend.repositories.LikeRepository;
import com.example.XCloneBackend.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    public LikeEntity setLikeEntity(Long userId,Long postId){
        LikeEntity likeEntity = new LikeEntity();
        postRepository.increaseLikeCount(postId);
        likeEntity.setUserId(userId);
        likeEntity.setPostId(postId);
        return likeRepository.save(likeEntity);
    }
    @Transactional
    public void disLike(Long userId,Long postId){
        try {

            LikeEntity likeEntity = likeRepository.getByUserIdAndPostId(userId,postId);
            likeRepository.deleteById(likeEntity.getId());
            postRepository.decreaseLikeCount(postId);

        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
