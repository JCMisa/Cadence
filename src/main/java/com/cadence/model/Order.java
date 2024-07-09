package com.cadence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data // to avoid creating getters and setters for each property
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //to make it auto increment
    private Long id;

    @ManyToOne // multiple order can accommodate by one user
    private User customer;

    @JsonIgnore
    @ManyToOne // one restaurant can have multiple order
    private ClothingShop clothingShop;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne // many order can be on one address
    private Address deliveryAddress;

    @OneToMany // one order can have many items in it
    private List<OrderItem> items;

    // private Payment payment

    private int totalItem;

    private Long totalPrice;
}
