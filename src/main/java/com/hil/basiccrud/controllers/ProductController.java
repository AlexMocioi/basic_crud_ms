package com.hil.basiccrud.controllers;

import com.hil.basiccrud.entity.dto.ProductDto;
import com.hil.basiccrud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("data/product")
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all")
    public Set<ProductDto> findAll() {
        logger.trace("findAll({})");

        return productService.findAll();
    }

    @PostMapping(value = "/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        logger.trace("creating({})" + productDto);

        return productService.createProduct(productDto);
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        logger.trace("creating({})" + productDto);

        return productService.updateProduct(productDto);
    }

    @DeleteMapping(value = "/{productId}")
    public Boolean deleteProduct(@PathVariable Long productId){

        return productService.deleteProduct(productId);
    }
}
