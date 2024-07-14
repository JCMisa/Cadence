package com.cadence.service;

import com.cadence.model.ShirtCategory;

import java.util.List;

public interface CategoryService
{
    public ShirtCategory createCategory (String name, Long userId) throws Exception;
    public List<ShirtCategory> findCategoryByClothingShopId(Long id) throws Exception;
    public ShirtCategory findCategoryById(Long id) throws Exception;

}
