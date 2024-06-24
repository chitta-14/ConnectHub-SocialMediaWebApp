package org.co.connecthub.controller;

import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.CommentDto;
import org.co.connecthub.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    //get
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto>get(@PathVariable Integer commentId){
        return new ResponseEntity<CommentDto>(this.commentService.findById(commentId), HttpStatus.OK);
    }
    //get all
    @GetMapping("/")
    public ResponseEntity<List<CommentDto>>getAll(){
        return new ResponseEntity<List<CommentDto>>(this.commentService.getAll(), HttpStatus.OK);
    }
    //post
    @PostMapping("/")
    public ResponseEntity<CommentDto>create(@RequestBody CommentDto commentDto){
        return new ResponseEntity<CommentDto>(this.commentService.createPost(commentDto), HttpStatus.CREATED);
    }
    //put
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto>update(@RequestBody CommentDto commentDto,@PathVariable Integer commentId){
        return new ResponseEntity<CommentDto>(this.commentService.updatePost(commentDto,commentId), HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse>delete(@PathVariable Integer commentId){
        return new ResponseEntity<ApiResponse>(this.commentService.deletePost(commentId), HttpStatus.OK);
    }
}
