package org.co.connecthub.service.serviceImpl;

import org.co.connecthub.entity.Category;
import org.co.connecthub.entity.Post;
import org.co.connecthub.entity.User;
import org.co.connecthub.exception.ResourceNotFoundException;
import org.co.connecthub.payload.*;
import org.co.connecthub.repository.CategoryRepository;
import org.co.connecthub.repository.PostRepository;
import org.co.connecthub.repository.UserRepository;
import org.co.connecthub.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        Category category=this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));

        Post post=this.modelMapper.map(postDto,Post.class);
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        this.postRepository.save(post);
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPost(Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAll() {
        List<Post> posts=this.postRepository.findAll();
        List<PostDto>postDtos=posts.stream().map((e)->this.modelMapper.map(e,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId,Integer userId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return this.modelMapper.map((this.postRepository.save(post)),PostDto.class);
    }
    @Override
    public ApiResponse deletePost(Integer postId,Integer userId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        this.postRepository.delete(post);
        ApiResponse apiResponse=new ApiResponse("Post is deleted sucessfully",true);
        return apiResponse;
    }

    @Override
    public PageResponse gettAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("asc"))?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        PageRequest p=PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePosts=this.postRepository.findAll(p);
        List<Post>posts=pagePosts.getContent();
        List<PostDto> postDtos=posts.stream().map((e)->this.modelMapper.map(e,PostDto.class)).collect(Collectors.toList());

        PageResponse pageResponse=new PageResponse();
        pageResponse.setContent(postDtos);
        pageResponse.setPageNumber(pagePosts.getNumber());
        pageResponse.setPageSize(pagePosts.getSize());
        pageResponse.setIsLastPage(pagePosts.isLast());
        pageResponse.setTotalPage(pagePosts.getTotalPages());
        pageResponse.setTotalNumberOfElements(pagePosts.getNumberOfElements());
        return pageResponse;
    }

    @Override
    public List<PostDto> findByUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post> posts=this.postRepository.findByUser(user);
        return posts.stream().map((e)->this.modelMapper.map(e,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findByCategory(Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Post> posts=this.postRepository.findByCategory(category);
        return posts.stream().map((e)->this.modelMapper.map(e,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findByTitleContaining(String keyword) {
        List<Post> posts=this.postRepository.findByTitleContaining(keyword);
        return posts.stream().map((e)->this.modelMapper.map(e,PostDto.class)).collect(Collectors.toList());
    }
}
