package com.cadence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // to avoid creating getters and setters for each property
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //to make it auto increment
    private Long id;

    @ManyToOne // many order item can have the same food, ex: 3 order item with same food like pizza
    private Shirt shirt;

    private int quantity;

    private Long totalPrice;
}
