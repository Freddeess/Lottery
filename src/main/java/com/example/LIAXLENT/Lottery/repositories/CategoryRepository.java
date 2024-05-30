package com.example.LIAXLENT.Lottery.repositories;

import com.example.LIAXLENT.Lottery.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
