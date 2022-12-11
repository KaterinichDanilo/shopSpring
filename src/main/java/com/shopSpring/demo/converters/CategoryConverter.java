package com.shopSpring.demo.converters;

import com.shopSpring.demo.dto.CategoryDto;
import com.shopSpring.demo.entities.Category;
import com.shopSpring.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final CategoryService categoryService;
    ProductConverter productConverter;

    public CategoryDto entityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProductDtoList(category.getProducts().stream().
                map(product -> productConverter.entityToDto(product)).collect(Collectors.toList()));
        return categoryDto;
    }
}
