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

    UnitMap unitMap;

    /**
     * create item 1 and item 2 and compare the value and return the result
     * */
    public Item getCheaper(Double amount1, String unit1, Double price1, Double amount2, String unit2, Double price2) {
        unitMap = new UnitMap();

        Item i1;
        Item i2;

        if (unitMap.getVolumeSet().contains(unit1)) {
            System.out.println("volume");
            i1 = new ItemByVolume();
            i2 = new ItemByVolume();

            ((ItemByVolume)i1).setAmount(amount1);
            ((ItemByVolume)i1).setUnit(unit1);
            ((ItemByVolume)i1).setPrice(price1);
            ((ItemByVolume)i2).setAmount(amount2);
            ((ItemByVolume)i2).setUnit(unit2);
            ((ItemByVolume)i2).setPrice(price2);
        }
        else if (unitMap.getMassSet().contains(unit1)) {
            System.out.println("mass");
            i1 = new ItemByMass();
            i2 = new ItemByMass();

            ((ItemByMass)i1).setAmount(amount1);
            ((ItemByMass)i1).setUnit(unit1);
            ((ItemByMass)i1).setPrice(price1);
            ((ItemByMass)i2).setAmount(amount2);
            ((ItemByMass)i2).setUnit(unit2);
            ((ItemByMass)i2).setPrice(price2);
        }
        else {
            System.out.println("length");
            i1 = new ItemByLength();
            i2 = new ItemByLength();

            ((ItemByLength)i1).setAmount(amount1);
            ((ItemByLength)i1).setUnit(unit1);
            ((ItemByLength)i1).setPrice(price1);
            ((ItemByLength)i2).setAmount(amount2);
            ((ItemByLength)i2).setUnit(unit2);
            ((ItemByLength)i2).setPrice(price2);
        }

        // invoke standardizeAmount --> convert current amount to the standard unit amount
        i1.standardizeAmount();
        i2.standardizeAmount();

        System.out.println(i1.getClass());

        return compareValue(i1, i2);
    }

    /**
     * compare Item 1 and Item 2's value and return the cheaper item
     * */
    private Item compareValue(Item i1, Item i2) {


        if (i1.compareTo(i2) > 0){
            return i1;
        }
        return i2;
    }
}
