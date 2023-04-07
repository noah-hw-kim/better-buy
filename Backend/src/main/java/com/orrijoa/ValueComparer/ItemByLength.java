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
 * standard unit for ItemByLength class is meter (m)
 * */
public class ItemByLength implements Item {

    @Id
    private ObjectId id;
    private String unit;
    private String name;
    private double price;
    private double amount;

    // convert the amount to meter (m)
    private double standardAmount;

    @Override
    public void standardizeAmount() {
        String unit = getUnit();

        switch (unit) {
            case "kilometer":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.KiloM, Qudt.Units.M)).doubleValue();
                break;
            case "centimeter":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.CentiM, Qudt.Units.M)).doubleValue();
                break;
            case "millimeter":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.MilliM, Qudt.Units.M)).doubleValue();
                break;
            case "mile":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.MI_US, Qudt.Units.M)).doubleValue();
                break;
            case "yard":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.YD, Qudt.Units.M)).doubleValue();
                break;
            case "foot":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.FT, Qudt.Units.M)).doubleValue();
                break;
            case "inch":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.IN, Qudt.Units.M)).doubleValue();
                break;
            default:
                break;
        }
    }

    @Override
    public int compareTo(Item other) {
        standardizeAmount();
        other.standardizeAmount();

        return Double.compare(standardAmount, ((ItemByLength)other).getStandardAmount());
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
