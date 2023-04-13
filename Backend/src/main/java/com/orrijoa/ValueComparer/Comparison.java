package com.orrijoa.ValueComparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
// this class will return the result of the value comparison
public class Comparison {
    private Item betterValue;
    // private Item worseValueItem;
    private Item[] comparedItems;
//    this represents how much the betterValueItem is cheaper than the other item average
    private double valueComparison;


//    1. save the array received as a global variable
//    2. compare each item value to get the best value item
    public Comparison(Item... itemList) {
        comparedItems = itemList;
        double sumOfPricePerBaseAmount = 0;
        double avgOfPricePerBaseAmount;

        try {
//            Determine the most valuable item by calculating the price per unit (price divided by amount) for each item and comparing them.
            for (int i = 0; i < comparedItems.length; i++) {
                sumOfPricePerBaseAmount += comparedItems[i].getPricePerBaseAmount();
                if (i == 0) {
                    betterValue = comparedItems[i];
                }
                else {
                    if (betterValue.compareTo(comparedItems[i]) > 0) {
                        betterValue = comparedItems[i];
                    }
                }
            }
//            remove the better value item and calculate the average
            sumOfPricePerBaseAmount -= betterValue.getPricePerBaseAmount();
            avgOfPricePerBaseAmount = sumOfPricePerBaseAmount / (comparedItems.length - 1);

            valueComparison = betterValue.getPricePerBaseAmount() / avgOfPricePerBaseAmount;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
