package com.cadence.service;

import com.cadence.model.ClothingShop;
import com.cadence.model.Shirt;
import com.cadence.model.ShirtCategory;
import com.cadence.repository.ShirtRepository;
import com.cadence.request.CreateShirtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShirtServiceImp implements ShirtService
{
    @Autowired
    private ShirtRepository shirtRepository;

    @Override
    public Shirt createShirt(CreateShirtRequest req, ShirtCategory category, ClothingShop clothingShop) {
        Shirt shirt = new Shirt();
        shirt.setCategory(category);
        shirt.setClothingShop(clothingShop);
        shirt.setName(req.getName());
        shirt.setDescription(req.getDescription());
        shirt.setPrice(req.getPrice());
        shirt.setSize(req.getSize());
        shirt.setImages(req.getImages());

        Shirt savedShirt = shirtRepository.save(shirt);
        clothingShop.getShirts().add(savedShirt);

        return savedShirt;
    }

    @Override
    public void deleteShirt(Long shirtId) throws Exception {
        Shirt shirt = findShirtById(shirtId);
        shirt.setClothingShop(null); // set the restaurant property of food to null
        shirtRepository.save(shirt);
    }

    @Override
    public List<Shirt> getClothingShopShirts(Long shopId, String shirtCategory) {
        List<Shirt> shirts = shirtRepository.findByClothingShopId(shopId);

        if(shirtCategory != null && !shirtCategory.isEmpty())
        {
            shirts = filterByCategory(shirts, shirtCategory);
        }

        return shirts;
    }
    private List<Shirt> filterByCategory(List<Shirt> shirts, String shirtCategory) {
        return shirts.stream().filter(shirt -> {
            if(shirt.getCategory() != null)
            {
                return shirt.getCategory().getName().equals(shirtCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Shirt> searchShirt(String keyword) {
        return shirtRepository.searchShirt(keyword);
    }

    @Override
    public Shirt findShirtById(Long shirtId) throws Exception {
        Optional<Shirt> optionalShirt = shirtRepository.findById(shirtId);
        if(optionalShirt.isEmpty()) {
            throw new Exception("Shirt not found");
        }
        return optionalShirt.get();
    }

    @Override
    public Shirt updateAvailabilityStatus(Long shirtId) throws Exception {
        Shirt shirt = findShirtById(shirtId);
        shirt.setAvailable(!shirt.isAvailable());
        return shirtRepository.save(shirt);
    }
}
