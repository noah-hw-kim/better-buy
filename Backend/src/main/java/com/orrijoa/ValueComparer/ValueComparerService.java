package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValueComparerService {

    @Autowired
    ValueComparerRepository valueComparerRepository;

    /**
     * 1. amount1 * unit1(standardized) * price1 compare
     * */
    public Item getCheaper(Double amount1, String unit1, Double price1, Double amount2, String unit2, Double price2) {
        return null;
    }

    public Item test() {
        System.out.println(Qudt.convert(new BigDecimal("500"), Qudt.Units.OZ, Qudt.Units.MilliT));
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
