package com.cadence.repository;

import com.cadence.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>
{
    public List<Order> findByCustomerId(Long userId);
    public List<Order> findByClothingShopId(Long shopId);
}
