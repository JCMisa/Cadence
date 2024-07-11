package com.cadence.service;

import com.cadence.dto.ShopDto;
import com.cadence.model.ClothingShop;
import com.cadence.model.User;
import com.cadence.request.CreateShopRequest;

import java.util.List;

public interface ClothingShopService
{
    public ClothingShop createShop(CreateShopRequest req, User user);

    public ClothingShop updateShop(Long shopId, CreateShopRequest updatedShop) throws Exception;

    public void deleteShop(Long shopId) throws Exception;

    public List<ClothingShop> getAllShop();

    public List<ClothingShop> searchShop(String keyword);

    public ClothingShop findShopById(Long id) throws Exception;

    public ClothingShop getShopByUserId(Long userId) throws Exception;

    public ShopDto addToFavorites(Long shopId, User user) throws Exception;

    public ClothingShop updateShopStatus(Long id) throws Exception;
}
