package com.example.XCloneBackend.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postDes;
    private String postImage;
    private LocalDateTime createdAt;
    private Long likeCount=0L;
    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;
}
