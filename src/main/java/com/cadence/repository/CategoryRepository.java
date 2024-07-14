package com.cadence.repository;

import com.cadence.model.ShirtCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<ShirtCategory, Long>
{
    public List<ShirtCategory> findByClothingShopId(Long id);
}
