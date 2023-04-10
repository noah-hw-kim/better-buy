package com.orrijoa.ValueComparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

// this class will return the result of the value comparison
public class Comparison {
    private Item betterValueItem;
    private Item worseValueItem;
    private double valueComparison;

    public Comparison(Item item1, Item item2) {
        betterValueItem = item1;
        worseValueItem = item2;

        // calculate how much the betterValueItem is cheaper than the worseValueItem = worseValueItem price per amount divide by betterValueItem price per amount
        valueComparison = item2.getPricePerBaseAmount() / item1.getPricePerBaseAmount();
    }

}
