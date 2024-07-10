package com.cadence.controller;

import com.cadence.service.ShirtService;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/shirt")
public class AdminShirtController
{
    @Autowired
    private ShirtService shirtService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClothingShopService restaurantService;

    // to be continued...
}
