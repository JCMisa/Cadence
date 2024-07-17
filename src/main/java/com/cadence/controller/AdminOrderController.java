package com.cadence.controller;

import com.cadence.model.Order;
import com.cadence.model.User;
import com.cadence.service.OrderService;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


    @GetMapping("/order/shop/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long id, @RequestParam(required = false) String orderStatus, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getClothingShopOrder(id, orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @PathVariable String orderStatus, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.updateOrder(id, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
