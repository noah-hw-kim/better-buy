package com.orrijoa.ValueComparer.models;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class Category {

    private static Map<String, List<String>> categories;

    public Category() {
        categories = new HashMap<>();

//        categories.put("Fruits", fruitsSubCategory);
//        categories.put("Vegetables", vegetablesSubCategory);
//        categories.put("Canned Goods", cannedGoodsSubCategory);
//        categories.put("Dairy", dairySubCategory);
//        categories.put("Meat", meatSubCategory);
//        categories.put("Fish and Seafood", fishAndSeafoodSubCategory);
//        categories.put("Deli", deliSubCategory);
//        categories.put("Condiments and Spices", condimentsAndSpicesSubCategory);
//        categories.put("Snacks", snacksSubCategory);
//        categories.put("Bread and Bakery", breadAndBakerySubCategory);
//        categories.put("Beverages", beveragesSubCategory);
//        categories.put("Pasta, Rice and Cereal", pastaAndRiceAndCerealSubCategory);
//        categories.put("Baking", bakingSubCategory);
//        categories.put("Frozen Foods", frozenFoodsSubCategory);
//        categories.put("Personal Care", personalCareSubCategory);
//        categories.put("Health Care", healthCareSubCategory);
//        categories.put("Household and Cleaning Supplies", householdAndCleaningSuppliesSubCategory);
//        categories.put("Baby Items", babyItemsSubCategory);
//        categories.put("Pet Care", petCareSubCategory);
//        categories.put("Etc", etcSubCategory);
    }

    @Bean
    public Map<String, List<String>> getCategories() {
        Map<String, List<String>> categoriesCopy = categories.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> List.copyOf(e.getValue())));

        return categories;
    }
}



































