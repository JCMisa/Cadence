package com.cadence.service;

import com.cadence.model.ClothingShop;
import com.cadence.model.Shirt;
import com.cadence.model.ShirtCategory;
import com.cadence.request.CreateShirtRequest;

import java.util.List;

public interface ShirtService
{
    public Shirt createShirt(CreateShirtRequest req, ShirtCategory category, ClothingShop clothingShop);
    void deleteShirt(Long shirtId) throws Exception;
    public List<Shirt> getClothingShopShirts(Long shirtId, String shirtCategory);
    public List<Shirt> searchShirt(String keyword);
    public Shirt findShirtById(Long shirtId) throws Exception;
    public Shirt updateAvailabilityStatus(Long shirtId) throws Exception;
}
