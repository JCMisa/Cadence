package com.cadence.service;

import com.cadence.model.ClothingShop;
import com.cadence.model.ShirtCategory;
import com.cadence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService
{
    @Autowired
    private ClothingShopService clothingShopService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ShirtCategory createCategory(String name, Long userId) throws Exception {
        ClothingShop shop = clothingShopService.getShopByUserId(userId);

        ShirtCategory category = new ShirtCategory();
        category.setName(name);
        category.setClothingShop(shop);

        return categoryRepository.save(category);
    }

    @Override
    public List<ShirtCategory> findCategoryByClothingShopId(Long id) throws Exception {
        ClothingShop shop = clothingShopService.findShopById(id);
        return categoryRepository.findByClothingShopId(shop.getId());
    }

    @Override
    public ShirtCategory findCategoryById(Long id) throws Exception {
        Optional<ShirtCategory> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty())
        {
            throw new Exception("Food Category not found");
        }

        return optionalCategory.get();
    }
}
