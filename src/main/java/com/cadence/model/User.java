package com.cadence.model;

import com.cadence.dto.ShopDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data // to avoid creating getters and setters for each property
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //to make it auto increment
    private Long id;

    private String fullName;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @JsonIgnore // ignore this property when retrieving the User instance's properties in a json format
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") // to say that a user has a one-to-many relationship with the Order model. It means that one user can have a multiple order
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<ShopDto> favorites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // because one user can have multiple addresses. CascadeType All so that once a User deleted, all related addresses for the user will also be deleted
    private List<Address> addresses = new ArrayList<>();
}
