package com.orrijoa.ValueComparer.service;

import com.orrijoa.ValueComparer.models.*;
import com.orrijoa.ValueComparer.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValueComparerService {

    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private UnitList unitList;
    @Autowired
    private Categories categories;

    public List<Item> createItems(List<Item> item) {
        // use item's hashcode for the id to prevent the duplicates
        List<Item> formatItem = new ArrayList<>();

        for (Item i : item) {
            Item saveItem = Item.builder()
                    .id(i.hashCode())
                    .unit(i.getUnit())
                    .price(i.getPrice())
                    .amount(i.getAmount())
                    .name(i.getName())
                    .brand(i.getBrand())
                    .store(i.getStore())
                    .category(i.getCategory())
                    .subCategory(i.getSubCategory())
                    .pricePerBaseAmount(calculatePricePerBaseAmount(i))
                    .build();

            formatItem.add(saveItem);
        }
        return itemRepo.saveAll(formatItem);
    }

    private double calculatePricePerBaseAmount(Item item) {
        // amount that converted to the base unit
        double result = -1;
        double baseAmount = UnitConverter.getStandardAmount(item.getUnit()) * item.getAmount();
        if (baseAmount != 0) {
            result = item.getPrice() / baseAmount;
        }
        return result;
    }

    public UnitList getUnitList() {
        return unitList;
    }

    public Categories getCategories() {
        return categories;
    }

    public List<Item> getAllItems() {
        return itemRepo.findAll(Sort.by(Sort.Direction.ASC, "category").and(Sort.by(Sort.Direction.ASC, "pricePerBaseAmount")));
    }

    public List<Item> getItemsByCategoryAndSubCategory(String category, String subCategory) {
        return itemRepo.findAllByCategoryAndSubCategoryOrderByPricePerBaseAmountAsc(category, subCategory);
    }

    // create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
    public Comparison getCheaper(String[] itemIdList) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < itemIdList.length; i++) {
            itemList.add(itemRepo.findItemById(Long.parseLong(itemIdList[i])));
        }
        return new Comparison(itemList);
    }
//
//        // create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
//    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2) {
//        Item item1 = itemRepo.findItemByUnitAndPriceAndAmount(unit1, price1, amount1);
//        Item item2 = itemRepo.findItemByUnitAndPriceAndAmount(unit2, price2, amount2);
//
//
//
//        //item1 = itemRepository.findItemByItem(item1).get();
//
//        return new Comparison(item1, item2);
//
////        Item item1 = new Item(unit1, price1, amount1);
////        Item item2 = new Item(unit2, price2, amount2);
////
////        return new Comparison(item1, item2);
//    }
//
//    // create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
//    public Comparison getCheaper(long item1id, long item2id) {
//        Item item1 = itemRepo.findItemById(item1id);
//        Item item2 = itemRepo.findItemById(item2id);
//
//
//
//        //item1 = itemRepository.findItemByItem(item1).get();
//
//        return new Comparison(item1, item2);
//
////        Item item1 = new Item(unit1, price1, amount1);
////        Item item2 = new Item(unit2, price2, amount2);
////
////        return new Comparison(item1, item2);
//    }
//
//
//
//    // create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
//    public Comparison getCheaper(long item1id, long item2id, long item3id) {
//        Item item1 = itemRepo.findItemById(item1id);
//        Item item2 = itemRepo.findItemById(item2id);
//        Item item3 = itemRepo.findItemById(item3id);
//
//        //item1 = itemRepository.findItemByItem(item1).get();
//
//        return new Comparison(item1, item2, item3);
//
////        Item item1 = new Item(unit1, price1, amount1);
////        Item item2 = new Item(unit2, price2, amount2);
////
////        return new Comparison(item1, item2);
//    }
//
//
//
//    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2, String unit3, double price3, double amount3) {
//        Item item1 = new Item(unit1, price1, amount1);
//        Item item2 = new Item(unit2, price2, amount2);
//        Item item3 = new Item(unit3, price3, amount3);
//
//        return new Comparison(item1, item2, item3);
//    }
//
//    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2, String unit3, double price3, double amount3, String unit4, double price4, double amount4) {
//        Item item1 = new Item(unit1, price1, amount1);
//        Item item2 = new Item(unit2, price2, amount2);
//        Item item3 = new Item(unit3, price3, amount3);
//        Item item4 = new Item(unit4, price4, amount4);
//
//        return new Comparison(item1, item2, item3, item4);
//    }
//
//    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2, String unit3, double price3, double amount3, String unit4, double price4, double amount4, String unit5, double price5, double amount5) {
//        Item item1 = new Item(unit1, price1, amount1);
//        Item item2 = new Item(unit2, price2, amount2);
//        Item item3 = new Item(unit3, price3, amount3);
//        Item item4 = new Item(unit4, price4, amount4);
//        Item item5 = new Item(unit5, price5, amount5);
//
//        return new Comparison(item1, item2, item3, item4, item5);
//    }
//
//    // compare Item 1 and Item 2's value and return the cheaper item
//    private Comparison compareValue(Item item1, Item item2) {
////        if (item1.compareTo(item2) > 0) {
////            // item2 is cheaper
////            return new Comparison(item2, item1);
////        }
////        // item1 is cheaper
////        return new Comparison(item1, item2);
//        return new Comparison(item1, item2);
//    }

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