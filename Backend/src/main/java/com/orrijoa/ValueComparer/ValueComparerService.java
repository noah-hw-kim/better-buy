package com.orrijoa.ValueComparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValueComparerService {

    @Autowired
    ValueComparerRepository valueComparerRepository;

    public Item getCheaper(Double amount1, String unit1, Double price1, Double amount2, String unit2, Double price2) {
        return null;
    }

    private void standardizeByUnit() {

    }

    private Item compareValue(Item i1, Item i2) {
        return null;
    }
}
