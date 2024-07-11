package com.cadence.controller;

import com.cadence.model.Shirt;
import com.cadence.model.User;
import com.cadence.service.ClothingShopService;
import com.cadence.service.ShirtService;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shirt")
public class ShirtController
{
    @Autowired
    private ShirtService shirtService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClothingShopService clothingShopService;

    @GetMapping("/search")
    public ResponseEntity<List<Shirt>> searchShirt(@RequestParam("query") String query,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Shirt> shirts = shirtService.searchShirt(query);

        return new ResponseEntity<>(shirts, HttpStatus.OK);
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<List<Shirt>> getShopShirt(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id,
            @RequestParam(required = false) String shirtCategory) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Shirt> shirts = shirtService.getClothingShopShirts(id, shirtCategory);

        return new ResponseEntity<>(shirts, HttpStatus.OK);
    }
}
