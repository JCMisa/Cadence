package com.cadence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data // to avoid creating getters and setters for each property
@AllArgsConstructor
@NoArgsConstructor
public class CartItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //to make it auto increment
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    private Shirt shirt;

    private int quantity;

    private  Long totalPrice;
}
