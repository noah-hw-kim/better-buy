package com.orrijoa.BetterBuy.models;

import lombok.Data;
import java.util.*;

/*
* Categories user can select to categorize items being compared
* */
@Data
public class Categories {
    private List<String> categories;

    public Categories() {
        categories = new ArrayList<>();
        Collections.addAll(categories, "Fruits", "Vegetables", "Canned Goods", "Dairy", "Meat", "Fish and Seafood", "Deli", "Condiments and Spices", "Snacks", "Bread and Bakery", "Beverages", "Pasta, Rice and Cereal", "Baking", "Frozen Foods", "Personal Care", "Health Care", "Household and Cleaning Supplies", "Baby Items", "Pet Care", "Etc");
    }

    public List<String> getCategories() {
        return List.copyOf(categories);
    }
}
