package org.co.connecthub.controller;

import org.co.connecthub.payload.CategoryDto;
import org.co.connecthub.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        return new ResponseEntity(this.categoryService.updateCategory(categoryDto,categoryId), HttpStatus.OK);
    }

    //get
    @GetMapping("/{categoryId}")
    public ResponseEntity getCategory(@PathVariable Integer categoryId){
        return new ResponseEntity(this.categoryService.findById(categoryId), HttpStatus.OK);
    }

    //getall
    @GetMapping("/")
    public ResponseEntity getCategory(){
        return new ResponseEntity(this.categoryService.findAll(), HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryId){
        return new ResponseEntity(this.categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }
}
