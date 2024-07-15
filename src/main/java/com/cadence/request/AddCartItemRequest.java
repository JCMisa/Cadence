package com.cadence.request;

import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest
{
    private Long shirtId;
    private int quantity;
}
