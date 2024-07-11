package com.cadence.request;

import com.cadence.model.Address;
import com.cadence.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateShopRequest
{
    private Long id;
    private String name;
    private String description;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
