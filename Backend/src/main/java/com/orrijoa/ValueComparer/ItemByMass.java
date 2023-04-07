package com.orrijoa.ValueComparer;


import io.github.qudtlib.Qudt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

/*
 * standard unit for ItemByMass class is gram (g)
 * */
public class ItemByMass implements Item {

    @Id
    private ObjectId id;
    private String unit;
    private String name;
    private double price;
    private double amount;

    // convert the amount to g (g)
    private double standardAmount;

    @Override
    public void standardizeAmount() {
        String unit = getUnit();

        switch (unit) {
            case "pound":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.LB, Qudt.Units.GM)).doubleValue();
                break;
            case "ounce":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.OZ, Qudt.Units.GM)).doubleValue();
                break;
            case "kilogram":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.KiloGM, Qudt.Units.GM)).doubleValue();
                break;
            case "milligram":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.MilliGM, Qudt.Units.GM)).doubleValue();
                break;
            default:
                break;
        }
    }

    @Override
    public int compareTo(Item other) {
        double thisItemValue = standardAmount * price;
        double otherItemValue = ((ItemByMass)other).getStandardAmount() * ((ItemByMass) other).getPrice();

        return Double.compare(thisItemValue, otherItemValue);
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode(){
        return 0;
    }
}
