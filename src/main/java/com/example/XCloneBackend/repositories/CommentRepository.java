package com.example.XCloneBackend.repositories;

import com.example.XCloneBackend.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    @Query("SELECT c FROM CommentEntity c WHERE c.postId = :id")
    List<CommentEntity> getByPostId(Long id);

}
