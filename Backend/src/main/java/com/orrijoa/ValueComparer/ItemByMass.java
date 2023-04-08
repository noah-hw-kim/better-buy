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
 * standard unit for ItemByMass class is gram (g). It is used to compareTo() method to compare two items value
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
        ItemByMass other = (ItemByMass) obj;

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
