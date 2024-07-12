package com.cadence.controller;

import com.cadence.dto.ShopDto;
import com.cadence.model.ClothingShop;
import com.cadence.model.User;
import com.cadence.service.ClothingShopService;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ClothingShopController
{
    @Autowired
    private ClothingShopService clothingShopService;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<ClothingShop>> searchShop(@RequestHeader("Authorization") String jwt, @RequestParam("query") String query) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<ClothingShop> shop = clothingShopService.searchShop(query);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ClothingShop>> getAllShop(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<ClothingShop> shop = clothingShopService.getAllShop();
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingShop> findShopById(@RequestHeader("Authorization") String jwt,
                                                    @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        ClothingShop shop = clothingShopService.findShopById(id);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<ShopDto> addToFavorites(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        ShopDto shopDto = clothingShopService.addToFavorites(id, user);

        return new ResponseEntity<>(shopDto, HttpStatus.OK);
    }
}
