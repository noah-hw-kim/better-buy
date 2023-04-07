package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    /**
     * create item 1 and item 2 and compare the value and return the result
     * */
    public Item getCheaper(Double amount1, String unit1, Double price1, Double amount2, String unit2, Double price2) {
        Item i1 = new Item();
        Item i2 = new Item();

        i1.setAmount(amount1);
        i1.setUnit(unit1);
        i1.setPrice(price1);
        i2.setAmount(amount2);
        i2.setUnit(unit2);
        i2.setPrice(price2);

        return compareValue(i1, i2);
    }

    public Item test() {
        System.out.println(Qudt.convert(new BigDecimal("1"), Qudt.Units.YD, Qudt.Units.KiloM));

        return null;
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
