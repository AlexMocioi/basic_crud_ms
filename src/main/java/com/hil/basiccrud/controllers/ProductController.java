package com.hil.basiccrud.controllers;

import com.hil.basiccrud.entity.dto.ProductDto;
import com.hil.basiccrud.services.ProductService;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/product")
public class ProductController extends AbstractController {

    //@Autowired
    private ProductService productService;

    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public Set<ProductDto> findAll() {
        logger.trace("findAll({})");

        Set rez =  productService.findAll();

        //niste cod care strica

        return rez;
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
    public Boolean deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping(value = "/{productId}")
    public Optional<ProductDto> findById(Long mockProductId) {
        return Optional.empty();
    }
}
