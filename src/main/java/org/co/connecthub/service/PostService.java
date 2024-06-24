package org.co.connecthub.service;

import org.co.connecthub.payload.*;

import java.util.List;

public interface PostService {
    //create
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    //get
    public PostDto getPost(Integer postId);
    //get all
    public List<PostDto>getAll();
    //update
    public PostDto updatePost(PostDto postDto,Integer postId,Integer userId);
    //delete
    public ApiResponse deletePost(Integer postId,Integer userId);
    public PageResponse gettAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //findByUser
    List<PostDto> findByUser(Integer userId);
    //findByCategory
    List<PostDto> findByCategory(Integer userId);
    //searching
    List<PostDto>findByTitleContaining(String keyword);
}
