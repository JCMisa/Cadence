package com.cadence.service;

import com.cadence.dto.ShopDto;
import com.cadence.model.Address;
import com.cadence.model.ClothingShop;
import com.cadence.model.User;
import com.cadence.repository.AddressRepository;
import com.cadence.repository.ClothingShopRepository;
import com.cadence.repository.UserRepository;
import com.cadence.request.CreateShopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClothingShopServiceImp implements ClothingShopService
{
    @Autowired
    private ClothingShopRepository clothingShopRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ClothingShop createShop(CreateShopRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());

        ClothingShop shop = new ClothingShop();
        shop.setAddress(address);
        shop.setContactInformation(req.getContactInformation());
        shop.setDescription(req.getDescription());
        shop.setImages(req.getImages());
        shop.setName(req.getName());
        shop.setOpeningHours(req.getOpeningHours());
        shop.setRegistrationDate(LocalDateTime.now());
        shop.setOwner(user);

        return clothingShopRepository.save(shop);
    }

    @Override
    public ClothingShop updateShop(Long shopId, CreateShopRequest updatedShop) throws Exception {
        ClothingShop shop = findShopById(shopId);

        // if a shop object was found by id and its properties have value, then it will set the previous value to the
        // property values of updatedShop instance
        if(shop.getDescription() != null)
        {
            shop.setDescription(updatedShop.getDescription());
        }

        if(shop.getName() != null)
        {
            shop.setName(updatedShop.getName());
        }

        return clothingShopRepository.save(shop);
    }

    @Override
    public void deleteShop(Long shopId) throws Exception {
        ClothingShop shop = findShopById(shopId);
        clothingShopRepository.delete(shop);
    }

    @Override
    public List<ClothingShop> getAllShop() {
        return clothingShopRepository.findAll();
    }

    @Override
    public List<ClothingShop> searchShop(String keyword) {
        return clothingShopRepository.findBySearchQuery(keyword);
    }

    @Override
    public ClothingShop findShopById(Long id) throws Exception {
        Optional<ClothingShop> optionalClothingShop = clothingShopRepository.findById(id);
        if(optionalClothingShop.isEmpty()) {
            throw new Exception("Shop not found");
        }
        return optionalClothingShop.get();
    }

    @Override
    public ClothingShop getShopByUserId(Long userId) throws Exception {
        ClothingShop shop = clothingShopRepository.findByOwnerId(userId);

        if(shop == null)
        {
            throw new Exception("Restaurant with owner id: " + userId + " does not exist");
        }
        return shop;
    }

    @Override
    public ShopDto addToFavorites(Long shopId, User user) throws Exception {
        ClothingShop shop = findShopById(shopId);

        ShopDto shopDto = new ShopDto();
        shopDto.setDescription(shop.getDescription());
        shopDto.setImages(shop.getImages());
        shopDto.setTitle(shop.getName());
        shopDto.setId(shopId);

        boolean isFavorite = false;
        List<ShopDto> favorites = user.getFavorites();
        for(ShopDto favorite : favorites) {
            if(favorite.getId().equals(shopId)) {
                isFavorite = true;
                break;
            }
        }
        // if the restaurant is already added as favorite, then remove it, otherwise, add it to favorites
        if(isFavorite)
        {
            favorites.removeIf(favorite -> favorite.getId().equals(shopId));
        }
        else {
            favorites.add(shopDto);
        }

        userRepository.save(user); // update the favorite shops of user

        return shopDto;
    }

    @Override
    public ClothingShop updateShopStatus(Long id) throws Exception {
        ClothingShop shop = findShopById(id);
        shop.setOpen(!shop.isOpen());
        return clothingShopRepository.save(shop);
    }
}
