package com.orrijoa.ValueComparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
// this class will return the result of the value comparison
public class Comparison {
    private Item betterValue;
    // private Item worseValueItem;
    private Item[] comparedList;
//    this represents how much the betterValueItem is cheaper than the other item average
    private double valueComparison;

//    public Comparison(Item item1, Item item2) {
//        betterValueItem = item1;
//        worseValueItem = item2;
//
//        // calculate how much the betterValueItem is cheaper than the worseValueItem = worseValueItem price per amount divide by betterValueItem price per amount
//        valueComparison = worseValueItem.getPricePerBaseAmount() / betterValueItem.getPricePerBaseAmount();
//    }


//    1. save the array received as a global variable
//    2. compare each item value to get the best value item
    public Comparison(Item... itemList) {
        comparedList = itemList;
        double sumOfPricePerBaseAmount = 0;
        double avgOfPricePerBaseAmount;

        try {
//            Determine the most valuable item by calculating the price per unit (price divided by amount) for each item and comparing them.
            for (int i = 0; i < comparedList.length; i++) {
                sumOfPricePerBaseAmount += comparedList[i].getPricePerBaseAmount();
                if (i == 0) {
                    betterValue = comparedList[i];
                }
                else {
                    if (betterValue.compareTo(comparedList[i]) > 0) {
                        betterValue = comparedList[i];
                    }
                }
            }
//            remove the better value item and calculate the average
            sumOfPricePerBaseAmount -= betterValue.getPricePerBaseAmount();
            avgOfPricePerBaseAmount = sumOfPricePerBaseAmount / (comparedList.length - 1);

            valueComparison = betterValue.getPricePerBaseAmount() / avgOfPricePerBaseAmount;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
