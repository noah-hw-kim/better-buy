package com.orrijoa.ValueComparer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
// this class will return the result of the value comparison
public class Comparison {
    private Item betterValue;
    private List<Item> comparedItemsList;
//    this represents how much the betterValueItem is cheaper than the other item average
//    for example, item1's pricePerBaseAmount ~= 56.7 and item2's pricePerBaseAmount ~= 85
//    item1 is approximately 0.33 times / 33% cheaper than item2
    private double valueComparison;

    public Comparison(List<Item> itemList) {
        comparedItemsList = new ArrayList<>(itemList);
        double sumOfPricePerBaseAmount = 0;
        double avgOfPricePerBaseAmount;

        try {
//            Determine the most valuable item by calculating the price per unit (price divided by amount) for each item and comparing them.
            for (int i = 0; i < comparedItemsList.size(); i++) {
                sumOfPricePerBaseAmount += comparedItemsList.get(i).getPricePerBaseAmount();
                if (i == 0) {
                    betterValue = comparedItemsList.get(i);
                }
                else {
                    if (betterValue.compareTo(comparedItemsList.get(i)) > 0) {
                        betterValue = comparedItemsList.get(i);
                    }
                }
            }
//            remove the better value item and calculate the average
            sumOfPricePerBaseAmount -= betterValue.getPricePerBaseAmount();
            avgOfPricePerBaseAmount = sumOfPricePerBaseAmount / (comparedItemsList.size() - 1);

            valueComparison = 1 - (betterValue.getPricePerBaseAmount() / avgOfPricePerBaseAmount);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    //    1. save the array received as a global variable
////    2. compare each item value to get the best value item
//    public Comparison(Item... itemList) {
//        comparedItems = itemList;
//        double sumOfPricePerBaseAmount = 0;
//        double avgOfPricePerBaseAmount;
//
//        try {
////            Determine the most valuable item by calculating the price per unit (price divided by amount) for each item and comparing them.
//            for (int i = 0; i < comparedItems.length; i++) {
//                sumOfPricePerBaseAmount += comparedItems[i].getPricePerBaseAmount();
//                if (i == 0) {
//                    betterValue = comparedItems[i];
//                }
//                else {
//                    if (betterValue.compareTo(comparedItems[i]) > 0) {
//                        betterValue = comparedItems[i];
//                    }
//                }
//            }
////            remove the better value item and calculate the average
//            sumOfPricePerBaseAmount -= betterValue.getPricePerBaseAmount();
//            avgOfPricePerBaseAmount = sumOfPricePerBaseAmount / (comparedItems.length - 1);
//
//            valueComparison = 1 - (betterValue.getPricePerBaseAmount() / avgOfPricePerBaseAmount);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

}
