package com.cadence.controller;

import com.cadence.model.Order;
import com.cadence.model.User;
import com.cadence.request.OrderRequest;
import com.cadence.service.OrderService;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req, user);

        PaymentResponse res = paymentService.createPaymentLink(order);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
