package com.orrijoa.ValueComparer;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class Category {

    private static Map<String, List<String>> categories;

    public Category() {
        // 11 subcategoris
        List<String> fruitsSubCategory = Arrays.asList("Apples", "bananas", "grapes", "oranges", "strawberries", "avocados", "peaches", "etc");
        List<String> vegetablesSubCategory = Arrays.asList("Potatoes", "onions", "carrots", "salad greens", "broccoli", "peppers", "tomatoes", "cucumbers", "etc");
        List<String> cannedGoodsSubCategory = Arrays.asList("Soup", "tuna", "fruit", "beans", "vegetables", "pasta sauce", "etc");
        List<String> dairySubCategory = Arrays.asList("Butter", "cheese", "eggs", "milk", "yogurt", "etc");
        List<String> meatSubCategory = Arrays.asList("Chicken", "beef", "pork", "sausage", "bacon", "etc");
        List<String> fishAndSeafoodSubCategory = Arrays.asList("Shrimp", "crab", "cod", "tuna", "salmon", "etc");
        List<String> deliSubCategory = Arrays.asList("Cheese", "salami", "ham", "turkey", "etc");
        List<String> condimentsAndSpicesSubCategory = Arrays.asList("Black pepper", "oregano", "cinnamon", "sugar", "olive oil", "ketchup", "mayonnaise", "etc");
        List<String> snacksSubCategory = Arrays.asList("Chips", "pretzels", "popcorn", "crackers", "nuts", "etc");
        List<String> breadAndBakerySubCategory = Arrays.asList("Bread", "tortillas", "pies", "muffins", "bagels", "cookies", "etc");
        List<String> beveragesSubCategory = Arrays.asList("Coffee", "teabags", "milk", "juice", "soda", "beer", "wine", "etc");
        List<String> pastaAndRiceAndCerealSubCategory = Arrays.asList("Oats", "granola", "brown rice", "white rice", "macaroni", "noodles", "etc");
        List<String> bakingSubCategory = Arrays.asList("Flour", "powdered", "sugar", "baking powder", "cocoa", "etc");
        List<String> frozenFoodsSubCategory = Arrays.asList("Pizza", "fish", "potatoes", "ready meals", "ice cream", "etc");
        List<String> personalCareSubCategory = Arrays.asList("Shampoo", "conditioner", "deodorant", "toothpaste", "dental floss", "etc");
        List<String> healthCareSubCategory = Arrays.asList("Saline", "band-aid", "cleaning alcohol", "pain killers", "antacids", "etc");
        List<String> householdAndCleaningSuppliesSubCategory = Arrays.asList("Laundry detergent", "dish soap", "dishwashing liquid", "paper towels", "tissues", "trash bags", "aluminum foil", "zip bags", "etc");
        List<String> babyItemsSubCategory = Arrays.asList("Baby food", "diapers", "wet wipes", "lotion", "etc");
        List<String> petCareSubCategory = Arrays.asList("Pet food", "kitty litter", "chew toys", "pet treats", "pet shampoo", "etc");

        categories = new HashMap<>();

        categories.put("Fruits", fruitsSubCategory);
        categories.put("Vegetables", vegetablesSubCategory);
        categories.put("Canned Goods", cannedGoodsSubCategory);
        categories.put("Dairy", dairySubCategory);
        categories.put("Meat", meatSubCategory);
        categories.put("Fish and Seafood", fishAndSeafoodSubCategory);
        categories.put("Deli", deliSubCategory);
        categories.put("Condiments and Spices", condimentsAndSpicesSubCategory);
        categories.put("Snacks", snacksSubCategory);
        categories.put("Bread and Bakery", breadAndBakerySubCategory);
        categories.put("Beverages", beveragesSubCategory);
        categories.put("Pasta, Rice and Cereal", pastaAndRiceAndCerealSubCategory);
        categories.put("Baking", bakingSubCategory);
        categories.put("Frozen Foods", frozenFoodsSubCategory);
        categories.put("Personal Care", personalCareSubCategory);
        categories.put("Health Care", healthCareSubCategory);
        categories.put("Household and Cleaning Supplies", householdAndCleaningSuppliesSubCategory);
        categories.put("Baby Items", babyItemsSubCategory);
        categories.put("Pet Care", petCareSubCategory);
    }

    public Map<String, List<String>> getCategories() {
        return categories;
    }
}



































