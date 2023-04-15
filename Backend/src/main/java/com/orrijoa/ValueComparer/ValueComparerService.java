package com.orrijoa.ValueComparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ValueComparerService {

    @Autowired
    ItemRepository itemRepo;

    public Item createItem(String unit, double price, double amount, String name, String brand, String store, String category, String subCategory) {
        return itemRepo.save(new Item(unit, price, amount, name, brand, store, category, subCategory));
    }

    public Map<String, List<String>> getUnitList() {
        UnitConverter unitList = new UnitConverter();
        return unitList.getUnitList();
    }

    public Map<String, List<String>> getCategories() {
        Category category = new Category();
        return category.getCategories();
    }

    public Optional<List<Item>> getAllItems() {
        return itemRepo.findAllByOrderByCategoryAscPricePerBaseAmountAsc();
    }

    public Optional<List<Item>> getItemsByCategoryAndSubCategory(String category, String subCategory) {
        return itemRepo.findAllByCategoryAndSubCategoryOrderByPricePerBaseAmountAsc(category, subCategory);
    }

        // create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2) {
        Item item1 = itemRepo.findItemByUnitAndPriceAndAmount(unit1, price1, amount1).get();
        Item item2 = itemRepo.findItemByUnitAndPriceAndAmount(unit2, price2, amount2).get();

        //item1 = itemRepository.findItemByItem(item1).get();

        return new Comparison(item1, item2);

//        Item item1 = new Item(unit1, price1, amount1);
//        Item item2 = new Item(unit2, price2, amount2);
//
//        return new Comparison(item1, item2);
    }

    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2, String unit3, double price3, double amount3) {
        Item item1 = new Item(unit1, price1, amount1);
        Item item2 = new Item(unit2, price2, amount2);
        Item item3 = new Item(unit3, price3, amount3);

        return new Comparison(item1, item2, item3);
    }

    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2, String unit3, double price3, double amount3, String unit4, double price4, double amount4) {
        Item item1 = new Item(unit1, price1, amount1);
        Item item2 = new Item(unit2, price2, amount2);
        Item item3 = new Item(unit3, price3, amount3);
        Item item4 = new Item(unit4, price4, amount4);

        return new Comparison(item1, item2, item3, item4);
    }

    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2, String unit3, double price3, double amount3, String unit4, double price4, double amount4, String unit5, double price5, double amount5) {
        Item item1 = new Item(unit1, price1, amount1);
        Item item2 = new Item(unit2, price2, amount2);
        Item item3 = new Item(unit3, price3, amount3);
        Item item4 = new Item(unit4, price4, amount4);
        Item item5 = new Item(unit5, price5, amount5);

        return new Comparison(item1, item2, item3, item4, item5);
    }

    // compare Item 1 and Item 2's value and return the cheaper item
    private Comparison compareValue(Item item1, Item item2) {
//        if (item1.compareTo(item2) > 0) {
//            // item2 is cheaper
//            return new Comparison(item2, item1);
//        }
//        // item1 is cheaper
//        return new Comparison(item1, item2);
        return new Comparison(item1, item2);
    }


//    // create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
//    public Item getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2) {
//        Item item1 = new Item(unit1, price1, amount1);
//        Item item2 = new Item(unit2, price2, amount2);
//
//        item1.setAmount(amount1);
//        item1.setUnit(unit1);
//        item1.setPrice(price1);
//        item2.setAmount(amount2);
//        item2.setUnit(unit2);
//        item2.setPrice(price2);
//
//        return compareValue(item1, item2);
//    }


//    // compare Item 1 and Item 2's value and return the cheaper item
//    private Item compareValue(Item item1, Item item2) {
//
////        compareTo method calculates each of the itemPricePerAmount and return
////        - positive num if i1 is cheaper than i2
////        - 0 if i1 and i2 has same value
////        - negative number. if i2 is cheaper than i1
//        if (item1.compareTo(item2) > 0) {
//            // item2 is cheaper
//            return item2;
//        }
//        // item1 is cheaper
//        return item1;
//    }



}
