package com.example.LIAXLENT.Lottery.controllers;

import com.example.LIAXLENT.Lottery.entities.Category;
import com.example.LIAXLENT.Lottery.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/categories")
    public List<Category> findAllCategories(){
        return categoryService.findAll();
    }
    @GetMapping("/categories/{id}")
    public Optional<Category> findCategory(@PathVariable int id){
        return categoryService.findById(id);
    }
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category){
        return categoryService.save(category);
    }
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteById(id);
    }
}
