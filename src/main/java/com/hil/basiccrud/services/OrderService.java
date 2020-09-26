package com.hil.basiccrud.services;

import com.hil.basiccrud.entity.dao.Order;
import com.hil.basiccrud.entity.dao.Product;
import com.hil.basiccrud.entity.dto.OrderDto;
import com.hil.basiccrud.entity.dto.ProductDto;
import com.hil.basiccrud.exceptions.ServiceException;
import com.hil.basiccrud.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService{

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Set<OrderDto> getAll(){
        List<Order> orders = (List<Order>) orderRepository.findAll();

        return orders.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toSet());
    }

    @Transactional
    public Set<OrderDto> getOrderById(Long userId) {
        Set<Order> orders = orderRepository.findAllByUserId(userId);

        return orders.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toSet());
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = mapper.map(orderDto, Order.class);
        order = orderRepository.save(order);
        return mapper.map(order,OrderDto.class);
    }

    @Transactional
    public OrderDto updateOrder(OrderDto orderDto) {
        Optional<Order> orderOptional = orderRepository.findById(orderDto.getId());
        System.out.println(orderDto);
        if (!orderOptional.isPresent())
            throw new ServiceException("The order that you are trying to update does not exist.");

        Order order = orderOptional.get();
        order.setAddress(orderDto.getAddress());
        order.setUserId(orderDto.getUserId());

        for (ProductDto product : orderDto.getProducts()) {
            order.getProducts().add(mapper.map(product, Product.class));
        }

        order = orderRepository.save(order);
        return mapper.map(order, OrderDto.class);
    }

    @Transactional
    public Boolean deleteOrder(Long orderId){
        if (!orderRepository.existsById(orderId)){
            return false;
        }
        orderRepository.deleteById(orderId);
        return true;
    }
}
