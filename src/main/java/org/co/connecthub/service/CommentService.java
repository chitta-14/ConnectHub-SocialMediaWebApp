package org.co.connecthub.service;

import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.CommentDto;

import java.util.List;

public interface CommentService {
    //get
    public CommentDto findById(Integer commentId);
    //get all
    public List<CommentDto>getAll();
    //post
    public CommentDto createPost(CommentDto commentDto);
    //put
    public CommentDto updatePost(CommentDto commentDto,Integer commentId);
    //delete
    public ApiResponse deletePost(Integer commentId);
}
