package org.co.connecthub.controller;

import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.AppConstants;
import org.co.connecthub.payload.PageResponse;
import org.co.connecthub.payload.PostDto;
import org.co.connecthub.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    //create
    @PostMapping("user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createUser(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        return new ResponseEntity<PostDto>(this.postService.createPost(postDto,userId,categoryId), HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{postId}/user/{userId}")
     public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Integer postId,
                                              @PathVariable Integer userId){
        return new ResponseEntity<PostDto>(this.postService.updatePost(postDto,postId,userId),HttpStatus.OK);
    }
    //get
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto>getPost(@PathVariable Integer postId){
        return new ResponseEntity<PostDto>(this.postService.getPost(postId),HttpStatus.OK );
    }
    //get all
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPost(){
        return new ResponseEntity<List<PostDto>>(this.postService.getAll(),HttpStatus.OK );
    }
    //delete
    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable Integer postId,
                                                 @PathVariable Integer userId){
        return new ResponseEntity<ApiResponse>(this.postService.deletePost(postId,userId),HttpStatus.OK);
    }
    @GetMapping("/p/")
    public ResponseEntity<PageResponse>gettAllPost(@RequestParam(value="pageNumber",defaultValue =AppConstants.pageNumber,required = false) Integer pageNumber,
                                                   @RequestParam(value="pageSize",defaultValue = AppConstants.pageSize,required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = AppConstants.sortBy,required = false) String sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue = AppConstants.sortDir,required = false) String sortDir){
          System.out.println(this.postService.gettAll(pageNumber,pageSize,sortBy,sortDir)+"<<<<<<>>>>>>>>");
        return new ResponseEntity<PageResponse>(this.postService.gettAll(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK );
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>>findByUser(@PathVariable Integer userId){
        return new ResponseEntity<>(this.postService.findByUser(userId),HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>>findByCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<List<PostDto>>(this.postService.findByUser(categoryId),HttpStatus.OK);
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>>findByTitleContaining(@PathVariable String keyword){
        return new ResponseEntity<>(this.postService.findByTitleContaining(keyword),HttpStatus.OK);
    }

}
