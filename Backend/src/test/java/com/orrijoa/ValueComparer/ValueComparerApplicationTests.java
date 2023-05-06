package com.orrijoa.ValueComparer;

import com.orrijoa.ValueComparer.models.Comparison;
import com.orrijoa.ValueComparer.models.Item;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

class ValueComparerApplicationTests {

    public static void main(String[] args) {
        Item item1 = new Item();
        Item item2 = new Item();

        item1.setPricePerBaseAmount(5.0);
        item2.setPricePerBaseAmount(3.0);

        List<Item> itemList = new ArrayList<>();

        itemList.add(item1);
        itemList.add(item2);

        Comparison comparison = new Comparison(itemList);
    }

}
