package com.cadence.repository;

import com.cadence.model.ClothingShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClothingShopRepository extends JpaRepository<ClothingShop, Long>
{
    @Query("SELECT c FROM ClothingShop c WHERE lower(c.name) LIKE lower(concat('%', :query, '%'))")
    List<ClothingShop> findBySearchQuery(String query);

    ClothingShop findByOwnerId(Long userId);
}
