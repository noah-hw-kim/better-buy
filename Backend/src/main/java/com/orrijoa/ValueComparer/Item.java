package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

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
    private double standardizeByCurrentUnit(Item other) {
        double convertedAmount;
        String currReference = other.getUnit();

        switch (currReference) {
            case "litter"
        }

        String currReferenceName = UnitMap.get(this.getUnit());
        String otherReferenceName = UnitMap.get(other.getUnit());

        double convertedAmount = Qudt.convert(new BigDecimal(35), Qudt.Units.{currReferenceName}, Qudt.Units.{otherReferenceName});

        return
    }

    @Override
    public int compareTo(Item other) {
        double currItemValue = price * amount;


        double otherItemValue = other.getPrice() * Qudt.convert(new BigDecimal(Double.));



        if (currItemValue > otherItemValue) {
            return 1;
        }
        return -1;
        return 0;
    }



    @Override
    public boolean equals(Object other) {
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
