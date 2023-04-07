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
        Item i1;
        Item i2;

        if (UnitMap.volumeSet.contains(unit1)) {
            i1 = new ItemByVolume();
            i2 = new ItemByVolume();

            ((ItemByVolume)i1).setAmount(amount1);
            ((ItemByVolume)i1).setUnit(unit1);
            ((ItemByVolume)i1).setPrice(price1);
            ((ItemByVolume)i2).setAmount(amount2);
            ((ItemByVolume)i2).setUnit(unit2);
            ((ItemByVolume)i2).setPrice(price2);
        }
        else if (UnitMap.massSet.contains(unit1)) {
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
            i1 = new ItemByLength();
            i2 = new ItemByLength();

            ((ItemByLength)i1).setAmount(amount1);
            ((ItemByLength)i1).setUnit(unit1);
            ((ItemByLength)i1).setPrice(price1);
            ((ItemByLength)i2).setAmount(amount2);
            ((ItemByLength)i2).setUnit(unit2);
            ((ItemByLength)i2).setPrice(price2);
        }
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
