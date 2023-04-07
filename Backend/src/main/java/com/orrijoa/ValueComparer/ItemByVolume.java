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
* standard unit for ItemByVolume class is milliliter (ml)
* */
public class ItemByVolume implements Item {

    @Id
    private ObjectId id;
    private String unit;
    private String name;
    private double price;
    private double amount;

    // convert the amount to milliliter (ml)
    private double standardAmount;

    @Override
    public void standardizeAmount() {
        String unit = getUnit();

        switch (unit) {
            case "gallon":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.GAL_US, Qudt.Units.MilliL)).doubleValue();
                break;
            case "quart":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.QT_US, Qudt.Units.MilliL)).doubleValue();
                break;
            case "pint":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.PINT_US, Qudt.Units.MilliL)).doubleValue();
                break;
            case "fluid ounces":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.OZ_VOL_US, Qudt.Units.MilliL)).doubleValue();
                break;
            case "litter":
                standardAmount = (Qudt.convert(new BigDecimal(amount), Qudt.Units.L, Qudt.Units.MilliL)).doubleValue();
                break;
            default:
                break;
        }
    }

    @Override
    public int compareTo(Item other) {
        standardizeAmount();
        other.standardizeAmount();

        return Double.compare(standardAmount, ((ItemByVolume)other).getStandardAmount());
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
