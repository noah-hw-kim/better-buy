package com.orrijoa.ValueComparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
* Reference for Qudt library
* Volume
* Metric
* Qudt.Units.L = Liter metric
* Qudt.Units.MilliL = Milliliter metric
* Qudt.Units.CentiM3 = Cubic Centimeter metric
*
*
* Qudt.Units.OZ_VOL_US = US oz imperial

*
* */

@Service
public class ValueComparerService {

    @Autowired
    ValueComparerRepository valueComparerRepository;

    UnitList unitList = new UnitList();

    /**
     * create item 1 and item 2 and compare the value and return the result
     * */
    public Item getCheaper(Double amount1, String unit1, Double price1, Double amount2, String unit2, Double price2) {
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

        i1.setAmount(amount1);
        i1.setUnit(unit1);
        i1.setPrice(price1);
        i2.setAmount(amount2);
        i2.setUnit(unit2);
        i2.setPrice(price2);

        // invoke standardizeAmount --> convert current amount to the standard unit amount
        i1.standardizeAmount();
        i2.standardizeAmount();

        return compareValue(i1, i2);
    }

    /**
     * compare Item 1 and Item 2's value and return the cheaper item
     * */
    private Item compareValue(Item i1, Item i2) {
        System.out.println(i1.getStandardAmount());
        System.out.println(i2.getStandardAmount());

        if (Item.compareTo(i1, i2) > 0){
            return i2;
        }
        return i1;
    }

    public UnitList getList() {
        return unitList;
    }
}
