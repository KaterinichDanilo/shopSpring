package com.shopSpring.demo.services;

import com.shopSpring.demo.entities.Category;
import com.shopSpring.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return (List<Category>)categoryRepository.findAll();
    }

    public Optional<Category> getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
