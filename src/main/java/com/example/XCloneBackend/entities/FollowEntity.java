package com.example.XCloneBackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "follow")
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.PERSIST)
    @JoinColumn(name = "followed_by",nullable = false)
    private UserEntity followedBy;

    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.PERSIST)
    @JoinColumn(name = "followed_to",nullable = false)
    private UserEntity followedTo;
}
