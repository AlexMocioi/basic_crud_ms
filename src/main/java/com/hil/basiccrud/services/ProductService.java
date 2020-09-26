package com.hil.basiccrud.services;

import com.hil.basiccrud.entity.dao.Product;
import com.hil.basiccrud.entity.dto.ProductDto;
import com.hil.basiccrud.exceptions.ServiceException;
import com.hil.basiccrud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Set<ProductDto> findAll() {
        logger.trace("finding all products({})");
        List<Product> products = (List<Product>) productRepository.findAll();

        return products.stream().map(product -> mapper.map(product, ProductDto.class)).collect(Collectors.toSet());
    }

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        logger.trace("creating({})" + productDto);
        Product product = productRepository.save(mapper.map(productDto, Product.class));

        return mapper.map(product, ProductDto.class);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        logger.trace("updating({})" + productDto);
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if (!optionalProduct.isPresent())
            throw new ServiceException("The product that you are trying to update does not exist.");

        Product product = mapper.map(productDto, Product.class);
        return mapper.map(productRepository.save(product),ProductDto.class);
    }

    @Transactional
    public Boolean deleteProduct(Long productId) {
        logger.trace("Deleting product with id({})" + productId);
        if (!productRepository.existsById(productId)) {
            return false;
        }
        productRepository.deleteById(productId);
        return true;
    }

    public ProductDto save(ProductDto dto){
        Product product=new Product();


        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        return mapper.map(productRepository.save(product),ProductDto.class);
    }
}
