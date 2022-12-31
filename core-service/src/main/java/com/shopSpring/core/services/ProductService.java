package com.shopSpring.core.services;

import com.shopSpring.api.ProductDto;
import com.shopSpring.api.ResourceNotFoundException;
import com.shopSpring.core.entities.Category;
import com.shopSpring.core.entities.Product;
import com.shopSpring.core.repositories.ProductRepository;
import com.shopSpring.core.specifications.ProductSpecification;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<Product> findAll(Specification<Product> specification, int page){
        return productRepository.findAll(specification, PageRequest.of(page, 10));
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

    public Specification<Product> createSpecificationBy(Integer minPrice, Integer maxPrice, String title){
        Specification<Product> specification = Specification.where(null);

        if (minPrice != null) {
            specification = specification.and(ProductSpecification.priceLessOrEqualThan(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecification.priceGreaterOrEqualThan(maxPrice));
        }
        if (title != null) {
            specification = specification.and(ProductSpecification.titleLike(title));
        }
        return specification;
    }

}
