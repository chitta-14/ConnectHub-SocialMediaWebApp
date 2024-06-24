package org.co.connecthub.service.serviceImpl;

import org.co.connecthub.entity.Comment;
import org.co.connecthub.exception.ResourceNotFoundException;
import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.CommentDto;
import org.co.connecthub.repository.CommentRepository;
import org.co.connecthub.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto findById(Integer commentId) {
        Comment comment=this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        return this.modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getAll() {
        List<Comment> list=this.commentRepository.findAll();
        return list.stream().map((e)->this.modelMapper.map(e,CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto createPost(CommentDto commentDto) {
        Comment comment=this.commentRepository.save(this.modelMapper.map(commentDto,Comment.class));
        return this.modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto updatePost(CommentDto commentDto, Integer commentId) {
        Comment comment=this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        comment.setContent(commentDto.getContent());
        Comment newComment=this.commentRepository.save(comment);
        return this.modelMapper.map(newComment,CommentDto.class);
    }

    @Override
    public ApiResponse deletePost(Integer commentId) {
        Comment comment=this.commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        this.commentRepository.delete(comment);
        ApiResponse apiResponse=new ApiResponse("Sucessfully deleted",true);
        return apiResponse;
    }
}
