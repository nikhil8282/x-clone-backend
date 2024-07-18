package com.example.XCloneBackend.entities;
import com.example.XCloneBackend.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String comment;
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Long postId;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;



    public CommentEntity(CommentRequestDto commentRequestDto){
        comment=commentRequestDto.getComment();
        postId=commentRequestDto.getPostId();
    }






}
