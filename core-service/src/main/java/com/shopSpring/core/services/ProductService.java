package com.shopSpring.core.services;

import com.shopSpring.api.ProductDto;
import com.shopSpring.api.ResourceNotFoundException;
import com.shopSpring.core.entities.Category;
import com.shopSpring.core.entities.Product;
import com.shopSpring.core.repositories.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private com.shopSpring.core.services.CategoryService categoryService;

    @PostConstruct
    private void init(){
        System.out.println(productRepository);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(product.getTitle());
        Category category = categoryService.getCategoryByTitle(productDto.getCategoryTitle())
                .orElseThrow(()->new ResourceNotFoundException("Category wasn't found title: " + productDto.getCategoryTitle()));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

}
