package com.shopSpring.core.controllers;

import com.shopSpring.api.ProductDto;
import com.shopSpring.core.converters.ProductConverter;
import com.shopSpring.core.entities.Product;
import com.shopSpring.core.services.CategoryService;
import com.shopSpring.core.services.ProductService;
import lombok.RequiredArgsConstructor;
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
//            @RequestParam(name = "p", defaultValue = "1") Integer page,
//            @RequestParam(name = "min_price", required = false) Integer minPrice,
//            @RequestParam(name = "max_price", required = false) Integer maxPrice,
//            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
//        if (page < 1) {
//            page = 1;
//        }
//        return productsService.findAll(minPrice, maxPrice, titlePart, page).map(
//                p -> productConverter.entityToDto(p)
//        );
        return productsService.findAll().stream().map(p ->
                productConverter.entityToDto(p)).collect(Collectors.toList());
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
