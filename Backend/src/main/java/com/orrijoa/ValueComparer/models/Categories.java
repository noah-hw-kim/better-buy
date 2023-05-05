package com.orrijoa.ValueComparer.models;

import lombok.Data;
import java.util.*;

/*
* categories user can select to categorize items being compared
* */
@Data
public class Categories {
    private List<String> categories;

    public Categories() {
        Category category = new Category();
        categories = category.getCategories();
    }

    // helper class used to keep Categories class abstracted
    private class Category {
        private List<String> categories;

        public Category() {
            categories = new ArrayList<>();
            Collections.addAll(categories, "Fruits", "Vegetables", "Canned Goods", "Dairy", "Meat", "Fish and Seafood", "Deli", "Condiments and Spices", "Snacks", "Bread and Bakery", "Beverages", "Pasta, Rice and Cereal", "Baking", "Frozen Foods", "Personal Care", "Health Care", "Household and Cleaning Supplies", "Baby Items", "Pet Care", "Etc");
        }

        // return defensive copy of the categories list
        public List<String> getCategories() {
            return List.copyOf(categories);
        }
    }
}
