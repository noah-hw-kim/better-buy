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
    // private Item worseValueItem;
    private Item[] itemArray;
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
        itemArray = itemList;
        double sumOfPricePerBaseAmount = 0;
        double avgOfPricePerBaseAmount;

        try {
//            Determine the most valuable item by calculating the price per unit (price divided by amount) for each item and comparing them.
            for (int i = 0; i < itemArray.length; i++) {
                sumOfPricePerBaseAmount += itemArray[i].getPricePerBaseAmount();
                if (i == 0) {
                    betterValueItem = itemArray[i];
                }
                else {
                    if (betterValueItem.compareTo(itemArray[i]) > 0) {
                        betterValueItem = itemArray[i];
                    }
                }
            }
//            remove the better value item and calculate the average
            sumOfPricePerBaseAmount -= betterValueItem.getPricePerBaseAmount();
            avgOfPricePerBaseAmount = sumOfPricePerBaseAmount / (itemArray.length - 1);

            valueComparison = betterValueItem.getPricePerBaseAmount() / avgOfPricePerBaseAmount;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
