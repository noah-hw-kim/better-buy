package com.orrijoa.ValueComparer;

import com.orrijoa.ValueComparer.models.Comparison;
import com.orrijoa.ValueComparer.models.Item;

import java.util.ArrayList;
import java.util.List;

class ValueComparerApplicationTests {

    public static void main(String[] args) {
        Item item1 = new Item();
        Item item2 = new Item();

        item1.setPricePerBaseUnit(5.0);
        item2.setPricePerBaseUnit(3.0);

        List<Item> itemList = new ArrayList<>();

        itemList.add(item1);
        itemList.add(item2);

        Comparison comparison = new Comparison(itemList);
    }

}
