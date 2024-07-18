package com.example.XCloneBackend.repositories;

import com.example.XCloneBackend.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {


    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> getByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.followers = u.followers-1 WHERE u.id=:id")
    void decraseFollower(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.following = u.following-1 WHERE u.id=:id")
    void decraseFollowing(Long id);



    @Query("SELECT u FROM UserEntity u WHERE u.userName ILike %:userName% or u.fullName ILike %:fullName%")
    List<UserEntity> getUserByUserNameAndFullName(@Param("userName") String userName,@Param("fullName") String fullName);


    @Query("SELECT u FROM UserEntity u LEFT JOIN FollowEntity f ON u.id = f.followedTo.id AND f.followedBy.id = :id WHERE f.followedBy.id IS NULL AND u.id != :id ORDER BY random() LIMIT 10")
    List<UserEntity> getSuggestedUsers(@Param("id") Long id);
}
