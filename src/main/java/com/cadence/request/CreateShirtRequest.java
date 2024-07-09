package com.cadence.request;

import com.cadence.model.ShirtCategory;
import lombok.Data;

import java.util.List;

@Data
public class CreateShirtRequest
{
    private String name;
    private String description;
    private Long price;
    private String size;

    private ShirtCategory category;
    private List<String> images;

    private Long clothingStoreId;
}
