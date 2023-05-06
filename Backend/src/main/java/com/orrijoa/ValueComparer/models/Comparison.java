package com.orrijoa.ValueComparer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
* 1. user click the compare button
* 2. Frontend send list of items to Backend controller
* 3. Service layer save the items in mongoDB using the list of items received
* 4. Service layer send the list of items to Frontend (ping-pong)
* 5. Frontend send items' id as a text to Backend
* 6. Service layer find items with itemIds and create a list of items
* 7. Service layer create a comparison object by passing the list of items
*/

/* Comparison obj that takes in itemList passed from service layer from mongoDB and does the following:
* 1. Find the cheapest item in the itemList
* 2. Find by how much the cheapest item is cheaper than other items
* */
public class Comparison {
    private Item betterItem;
    private List<Item> comparedItemsList;
    //    this represents how much the betterValueItem is cheaper than the other item average
    //    for example, item1's pricePerBaseAmount ~= 56.7 and item2's pricePerBaseAmount ~= 85
    //    item1 is approximately 0.33 times / 33% cheaper than item2
    private double valueComparison;
    private List<Comparison> comparisons;

    public Comparison(List<Item> itemList) {
        comparedItemsList = new ArrayList<>(itemList);

        double sumOfPricePerBaseAmount = 0;
        double avgOfPricePerBaseAmount;


        // Determine the most valuable item by calculating the price per unit (price divided by amount) for each item and comparing them.
        for (int i = 0; i < comparedItemsList.size(); i++) {
            sumOfPricePerBaseAmount += comparedItemsList.get(i).getPricePerBaseAmount();
            if (i == 0) {
                betterItem = comparedItemsList.get(i);
            }
            else {
                if (betterItem.compareTo(comparedItemsList.get(i)) > 0) {
                    betterItem = comparedItemsList.get(i);
                }
            }
        }

        // remove the better value item and calculate the average
        sumOfPricePerBaseAmount -= betterItem.getPricePerBaseAmount();
        avgOfPricePerBaseAmount = sumOfPricePerBaseAmount / (comparedItemsList.size() - 1);

        valueComparison = 1 - (betterItem.getPricePerBaseAmount() / avgOfPricePerBaseAmount);
    }

    private double getValueComparison(double sum) {
        // remove the better value item and calculate the average
        sum -= betterItem.getPricePerBaseAmount();
        double avgOfPricePerBaseAmount = sum / (comparedItemsList.size() - 1);

        valueComparison = 1 - (betterItem.getPricePerBaseAmount() / avgOfPricePerBaseAmount);

        return valueComparison;
    }

}
