package com.cadence.request;

import com.cadence.model.Address;
import lombok.Data;

@Data
public class OrderRequest
{
    private Long clothingShopId;
    private Address deliveryAddress;
}
