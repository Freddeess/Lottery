package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Category;
import com.example.LIAXLENT.Lottery.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    @Override
    public Optional<Category> findById(int id){
        return categoryRepository.findById(id);
    }
    @Override
    public Category save(Category category){
        return categoryRepository.save(category);
    }
    @Override
    public void deleteById(int id){
        categoryRepository.deleteById(id);
    }
}
