package com.example.LIAXLENT.Lottery.services;

import com.example.LIAXLENT.Lottery.entities.Category;


import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(int id);

    Category save (Category category);

    void deleteById(int id);
}
