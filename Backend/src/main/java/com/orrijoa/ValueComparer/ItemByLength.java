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


public class ItemByLength implements Item {

    @Id
    private ObjectId id;
    private String unit;
    private String name;
    private double price;
    private double amount;

    /*
     * the amount after converting the current amount to meter (m)
     * */
    private double standardAmount;

    // convert the amount to meter (m). It is used to compareTo() method to compare two items value
    @Override
    public void standardizeAmount() {
        UnitList ul = new UnitList();
        double base = ul.get(unit);
        standardAmount = base * amount;

        /*
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
                standardAmount = amount;
                break;
        }

         */
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ItemByLength other = (ItemByLength) obj;

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
