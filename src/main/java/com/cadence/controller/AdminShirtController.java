package com.cadence.controller;

import com.cadence.model.ClothingShop;
import com.cadence.model.Shirt;
import com.cadence.model.User;
import com.cadence.request.CreateShirtRequest;
import com.cadence.response.MessageResponse;
import com.cadence.service.ClothingShopService;
import com.cadence.service.ShirtService;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/shirt")
public class AdminShirtController
{
    @Autowired
    private ShirtService shirtService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClothingShopService shopService;

    @PostMapping
    public ResponseEntity<Shirt> createShirt(@RequestBody CreateShirtRequest req,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        ClothingShop shop = shopService.findShopById(req.getClothingShopId());
        Shirt shirt = shirtService.createShirt(req, req.getCategory(), shop);

        return new ResponseEntity<>(shirt, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteShirt(@PathVariable Long id,
                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        shirtService.deleteShirt(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Shirt deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shirt> updateShirtAvailabilityStatus(@PathVariable Long id,
                                                          @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Shirt shirt = shirtService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(shirt, HttpStatus.CREATED);
    }
}
