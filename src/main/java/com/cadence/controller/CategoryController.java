package com.cadence.controller;

import com.cadence.model.ShirtCategory;
import com.cadence.model.User;
import com.cadence.service.CategoryService;
import com.cadence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;


    @PostMapping("/admin/category")
    public ResponseEntity<ShirtCategory> createCategory(@RequestBody ShirtCategory category, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        ShirtCategory createdCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/shop/{id}")
    public ResponseEntity<List<ShirtCategory>> getShopCategory(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<ShirtCategory> categories = categoryService.findCategoryByClothingShopId(id);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ShirtCategory> getCategoryById(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        ShirtCategory category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category,HttpStatus.OK );
    }
}
