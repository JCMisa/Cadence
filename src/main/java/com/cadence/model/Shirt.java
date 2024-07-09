package com.cadence.model;

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
public class Shirt
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //to make it auto increment
    private Long id;

    private String name;

    private String description;

    private Long price;

    private String size;

    @ManyToOne // multiple foods can have same category
    private ShirtCategory category;

    @Column(length = 1000)
    @ElementCollection // gives separate table for shirt images
    private List<String> images;

    private  boolean available;

    @ManyToOne // one Shop can have multiple Food
    private ClothingShop clothingShop; // which shop is this food belongs

    private Date creationDate;
}
