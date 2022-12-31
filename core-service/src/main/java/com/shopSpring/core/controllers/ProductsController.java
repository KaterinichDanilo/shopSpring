package com.shopSpring.core.controllers;

import com.shopSpring.api.ProductDto;
import com.shopSpring.core.converters.ProductConverter;
import com.shopSpring.core.entities.Product;
import com.shopSpring.core.services.CategoryService;
import com.shopSpring.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productsService;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }

        Specification<Product> specification = productsService.createSpecificationBy(minPrice, maxPrice, titlePart);

        return productsService.findAll(specification, page - 1).map(productConverter::entityToDto).getContent();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productsService.findById(id).orElseThrow(() -> new RuntimeException("Product not found id = " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product product = productsService.createNewProduct(productDto);
        return productConverter.entityToDto(product);
    }
}
