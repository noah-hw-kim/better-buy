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
* standard unit for ItemByVolume class is milliliter (ml). It is used to compareTo() method to compare two items value
* */
public class ItemByVolume implements Item {

    @Id
    private ObjectId id;
    private String unit;
    private String name;
    private double price;
    private double amount;


    /*
     * the amount after converting the current amount to milliliter (ml)
     * */
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
                standardAmount = amount;
                break;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ItemByVolume other = (ItemByVolume) obj;

        return other.id.equals(id) && other.unit.equals(unit) && other.name.equals(name) && other.price == price && other.amount == amount;
    }

    @Override
    public int hashCode(){
        // creating hashcode() with custom : starting point 1, prime multiplier 31
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (int) price;
        result = prime * result + (int) amount;

        return result;
    }
}
