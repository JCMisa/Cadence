package com.cadence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // to avoid creating getters and setters for each property
@AllArgsConstructor
@NoArgsConstructor
public class ShirtCategory
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //to make it auto increment
    private Long id;

    private String name; // category name like polo shirt, t-shirt, office shirts, etc.

    @ManyToOne
    @JsonIgnore
    private ClothingShop clothingShop;
}
