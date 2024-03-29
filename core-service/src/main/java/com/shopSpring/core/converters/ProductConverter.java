package com.shopSpring.core.converters;

import com.shopSpring.api.ProductDto;
import com.shopSpring.core.entities.Category;
import com.shopSpring.core.entities.Product;
import com.shopSpring.core.services.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    private CategoryService categoryService;
    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getCategory().getTitle());
        productDto.setId(product.getId());
        return productDto;
    }
    public Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(product.getTitle());
        Category category = categoryService.getCategoryByTitle(productDto.getCategoryTitle())
                .orElseThrow(()->new RuntimeException("Category wasn't found title: " + productDto.getCategoryTitle()));
        product.setCategory(category);
        return product;
    }
}
