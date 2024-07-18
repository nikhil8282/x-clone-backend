package com.example.XCloneBackend.repositories;

import com.example.XCloneBackend.entities.FollowEntity;
import com.example.XCloneBackend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity,Long> {

    @Query("SELECT f.followedBy FROM FollowEntity f WHERE f.followedTo.id= :id")
    List<UserEntity> getFollowers(@Param("id") Long id);


    @Query("Select f.followedTo from FollowEntity f where f.followedBy.id = :id")
    List<UserEntity> getFollows(@Param("id") Long id);

    @Query("Select f from FollowEntity f where f.followedBy.id = :followedBy and f.followedTo.id = :followedTo")
    Optional<FollowEntity> getByFollowedByAndFollowedTo(@Param("followedBy") Long followedBy, @Param("followedTo") Long followedTo);
}
