package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item implements Comparable<Item> {
    @Id
    private ObjectId id;
    private String unit;
    private String name;
    private double price;
    private double amount;

    /*
     * convert unit1 and unit2 to the metric unit
     * */
    private void standardizeByUnit() {

    }

    @Override
    public int compareTo(Item i) {

        /*
        int currItemValue = price * amount * standardizeByUnit(unit);
        int otherItemValue = i.getPrice() * i.getAmount() * i.standardizeByUnit(i.getUnit());



        if (currItemValue > otherItemValue) {
            return 1;
        }
        return -1;
        */
        return 0;
    }



    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }

}
