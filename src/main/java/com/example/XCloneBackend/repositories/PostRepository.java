package com.example.XCloneBackend.repositories;

import com.example.XCloneBackend.entities.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE PostEntity p SET p.likeCount = p.likeCount + 1 WHERE p.id = :id")
    void increaseLikeCount(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE PostEntity p SET p.likeCount = p.likeCount-1 WHERE p.id = :id")
    void decreaseLikeCount(Long id);



    @Query("SELECT p FROM PostEntity p WHERE p.user.id = :id or p.user.id IN (SELECT f.followedTo.id FROM FollowEntity f WHERE f.followedBy.id = :id)")
    List<PostEntity> findAllForUser(Long id);


    @Query("SELECT p FROM PostEntity p WHERE p.user.id = :id")
    List<PostEntity> findAllByUserId(Long id);
//

}
