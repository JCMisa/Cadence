package com.cadence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClothingShop
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //to make it auto increment
    private Long id;

    @OneToOne // one restaurant to one user and vice versa
    private User owner;

    private  String name; // name of the shop

    private String description;

    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    private String openingHours;

    @JsonIgnore
    @OneToMany(mappedBy = "clothingShop", cascade = CascadeType.ALL, orphanRemoval = true) // one restaurant can have many orders
    private  List<Order> orders = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private  List<String> images;

    private LocalDateTime registrationDate;

    private boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "clothingShop", cascade = CascadeType.ALL) // so that when restaurant is deleted, all foods inside that will also be deleted
    private List<Shirt> shirts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "clothingShop", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();
}
