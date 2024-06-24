package org.co.connecthub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String content;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
}
