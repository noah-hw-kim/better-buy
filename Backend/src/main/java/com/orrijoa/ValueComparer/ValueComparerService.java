package com.orrijoa.ValueComparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValueComparerService {

    @Autowired
    ValueComparerRepository valueComparerRepository;

    UnitList unitList = new UnitList();

        // create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
    public Comparison getCheaper(String unit1, double price1, double amount1, String unit2, double price2, double amount2) {
        Item item1 = new Item(unit1, price1, amount1);
        Item item2 = new Item(unit2, price2, amount2);

        item1.setAmount(amount1);
        item1.setUnit(unit1);
        item1.setPrice(price1);
        item2.setAmount(amount2);
        item2.setUnit(unit2);
        item2.setPrice(price2);

        return compareValue(item1, item2);
    }

        // compare Item 1 and Item 2's value and return the cheaper item
    private Comparison compareValue(Item item1, Item item2) {
        if (item1.compareTo(item2) > 0) {
            // item2 is cheaper
            return new Comparison(item2, item1);
        }
        // item1 is cheaper
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

    public UnitList getList() {
        return unitList;
    }

}
