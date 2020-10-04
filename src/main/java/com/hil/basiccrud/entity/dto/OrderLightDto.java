package com.hil.basiccrud.entity.dto;

public class OrderLightDto {
    private Long id;
    private Long userId;

    public OrderLightDto(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public OrderLightDto() {
    }
}
