package com.orrijoa.ValueComparer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/* Comparison obj takes in a list of items passed from service layer from mongoDB.
*  It then stores the best value item and the list of items compared.
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comparison {
    private Item bestValItem;
    private List<Item> comparedItemsList;

    public Comparison(List<Item> itemList) {
        comparedItemsList = new ArrayList<>(itemList);
        bestValItem = calcBestValItem();
    }

    // Determine the best value item by comparing their base unit equivalent amounts.
    private Item calcBestValItem() {
        Item bestItem = null;

        for (int i = 0; i < comparedItemsList.size(); i++) {
            if (i == 0) {
                bestItem = comparedItemsList.get(i);
            }
            else {
                if (bestItem.compareTo(comparedItemsList.get(i)) > 0) {
                    bestItem = comparedItemsList.get(i);
                }
            }
        }
        return bestItem;
    }


}
