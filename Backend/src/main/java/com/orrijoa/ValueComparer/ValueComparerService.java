package com.orrijoa.ValueComparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*

* */

@Service
public class ValueComparerService {

    @Autowired
    ValueComparerRepository valueComparerRepository;

    UnitList unitList = new UnitList();

    /**
     * create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
     * */
    public Item getCheaper(Double amount1, String unit1, Double price1, Double amount2, String unit2, Double price2) {
        Item item1 = new Item();
        Item item2 = new Item();

        item1.setAmount(amount1);
        item1.setUnit(unit1);
        item1.setPrice(price1);
        item2.setAmount(amount2);
        item2.setUnit(unit2);
        item2.setPrice(price2);

        return compareValue(item1, item2);


        /*
        Item i1;
        Item i2;

        if (unitList.getVolumeSet().contains(unit1)) {
            i1 = new ItemByVolume();
            i2 = new ItemByVolume();
        }
        else if (unitList.getMassSet().contains(unit1)) {
            i1 = new ItemByMass();
            i2 = new ItemByMass();
        }
        else {
            i1 = new ItemByLength();
            i2 = new ItemByLength();
        }

        // set items' amount unit and price
        i1.setAmount(amount1);
        i1.setUnit(unit1);
        i1.setPrice(price1);
        i2.setAmount(amount2);
        i2.setUnit(unit2);
        i2.setPrice(price2);

        // invoke standardizeAmount --> convert current amount to the standard unit amount and save in the obj as a private
        i1.standardizeAmount();
        i2.standardizeAmount();

        return compareValue(i1, i2);

         */
    }

    /**
     * compare Item 1 and Item 2's value and return the cheaper item
     * */
    private Item compareValue(Item item1, Item item2) {
        /*
        compareTo method calculates each of the itemPricePerAmount and return
        - positive num if i1 is cheaper than i2
        - 0 if i1 and i2 has same value
        - negative number. if i2 is cheaper than i1
         */

        if (item1.compareTo(item2) > 0) {
            // item2 is cheaper
            return item2;
        }
        // item1 is cheaper
        return item1;

        /*
        if (Item.compareTo(i1, i2) > 0){
            // i2 is cheaper
            return i2;
        }
        // i1 is cheaper
        return i1;

         */
    }

    public UnitList getList() {
        return unitList;
    }
}
