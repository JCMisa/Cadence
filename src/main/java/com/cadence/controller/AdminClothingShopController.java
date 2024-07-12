package com.cadence.controller;

import com.cadence.model.ClothingShop;
import com.cadence.model.User;
import com.cadence.request.CreateShopRequest;
import com.cadence.response.MessageResponse;
import com.cadence.service.ClothingShopService;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/shop")
public class AdminClothingShopController
{
    @Autowired
    private ClothingShopService clothingShopService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<ClothingShop> createShop(@RequestBody CreateShopRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        ClothingShop shop = clothingShopService.createShop(req, user);
        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothingShop> updateShop(@RequestBody CreateShopRequest req, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        ClothingShop shop = clothingShopService.updateShop(id, req);
        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteShop(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        clothingShopService.deleteShop(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Clothing Shop deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ClothingShop> updateShopStatus(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        ClothingShop shop = clothingShopService.updateShopStatus(id);

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<ClothingShop> findShopByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        ClothingShop shop = clothingShopService.getShopByUserId(user.getId());

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
}
