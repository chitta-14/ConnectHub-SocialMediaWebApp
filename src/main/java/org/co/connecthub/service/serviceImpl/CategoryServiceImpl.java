package org.co.connecthub.service.serviceImpl;

import org.co.connecthub.entity.Category;
import org.co.connecthub.exception.ResourceNotFoundException;
import org.co.connecthub.payload.ApiResponse;
import org.co.connecthub.payload.CategoryDto;
import org.co.connecthub.repository.CategoryRepository;
import org.co.connecthub.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category category= this.categoryRepository.save(this.modelMapper.map(categoryDto, Category.class));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Categpry","categoryId",categoryId));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryAbout(categoryDto.getCategoryAbout());
        return this.modelMapper.map(this.categoryRepository.save(category),CategoryDto.class);
    }

    @Override
    public CategoryDto findById(Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Categpry","categoryId",categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories=this.categoryRepository.findAll();
        return categories.stream().map((e)->this.modelMapper.map(e,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public ApiResponse deleteCategory(Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Categpry","categoryId",categoryId));
        this.categoryRepository.delete(category);
        ApiResponse apiResponse=new ApiResponse("deleted sucessfully",true);
        return apiResponse;
    }
}
