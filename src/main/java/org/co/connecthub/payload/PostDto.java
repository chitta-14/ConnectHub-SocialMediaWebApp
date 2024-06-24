package org.co.connecthub.payload;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private Date addedDate;
    private UserDto user;

}
