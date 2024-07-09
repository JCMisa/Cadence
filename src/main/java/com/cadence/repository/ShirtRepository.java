package com.cadence.repository;

import com.cadence.model.Shirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShirtRepository extends JpaRepository<Shirt, Long>
{
    List<Shirt> findByClothingShopId(Long clothingShopId);

    @Query("SELECT s FROM Shirt s WHERE s.name LIKE %:keyword% OR s.shirtCategory.name LIKE %:keyword% OR s.size LIKE" +
            " %:keyword%")
    List<Shirt> searchShirt(@Param("keyword") String keyword);
}
