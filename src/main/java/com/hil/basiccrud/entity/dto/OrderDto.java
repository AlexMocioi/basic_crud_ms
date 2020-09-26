package com.hil.basiccrud.entity.dto;

import java.util.Set;

public class OrderDto {

    private Long id;

    private String address;

    private Long userId;

    Set<ProductDto> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                ", products=" + products +
                '}';
    }
}
