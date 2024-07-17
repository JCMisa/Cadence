package com.cadence.service;

import com.cadence.model.Order;
import com.cadence.model.User;
import com.cadence.request.OrderRequest;

import java.util.List;

public interface OrderService
{
    public Order createOrder(OrderRequest order, User user) throws Exception;
    public Order updateOrder(Long orderId, String orderStatus) throws Exception;
    public void cancelOrder(Long orderId) throws Exception;
    public List<Order> getUserOrder(Long userId) throws Exception;
    public List<Order> getClothingShopOrder(Long shopId, String orderStatus) throws Exception;
    public Order findOrderById(Long orderId) throws Exception;
}
