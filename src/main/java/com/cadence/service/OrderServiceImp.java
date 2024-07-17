package com.cadence.service;

import com.cadence.model.*;
import com.cadence.repository.AddressRepository;
import com.cadence.repository.OrderItemRepository;
import com.cadence.repository.OrderRepository;
import com.cadence.repository.UserRepository;
import com.cadence.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClothingShopService clothingShopService;
    @Autowired
    private CartService cartService;


    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippingAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);

        if(!user.getAddresses().contains(savedAddress))
        {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        ClothingShop shop = clothingShopService.findShopById(order.getClothingShopId());

        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setClothingShop(shop);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cart.getItems())
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setShirt(cartItem.getShirt());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(createdOrder);
        shop.getOrders().add(savedOrder);

        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);

        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING"))
        {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getClothingShopOrder(Long shopId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByClothingShopId(shopId);

        if(orderStatus != null) {
            orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if(optionalOrder.isEmpty())
        {
            throw new Exception("Order not found");
        }

        return optionalOrder.get();
    }
}
