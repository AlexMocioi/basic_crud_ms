package com.hil.basiccrud.repositories;

import com.hil.basiccrud.entity.dao.Order;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Set<Order> findAllByUserId(Long userId);
}
