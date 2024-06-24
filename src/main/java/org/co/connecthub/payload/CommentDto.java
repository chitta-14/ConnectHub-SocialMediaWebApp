package org.co.connecthub.payload;

import lombok.Data;

@Data
public class CommentDto {
    private Integer commentId;
    private String content;
    private UserDto user;
    private PostDto post;

}
