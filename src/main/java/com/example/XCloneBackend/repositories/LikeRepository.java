package com.example.XCloneBackend.repositories;
import com.example.XCloneBackend.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {

        @Query("SELECT l.postId from LikeEntity l WHERE l.userId = :userId ")
        List<Long> getByUserId(Long userId);

        @Query("SELECT l from LikeEntity l where l.userId = :userId and l.postId = :postId")
        LikeEntity getByUserIdAndPostId(@Param("userId") Long userId,@Param("postId") Long postId);



}
