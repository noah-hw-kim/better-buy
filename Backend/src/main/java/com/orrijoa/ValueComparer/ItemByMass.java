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

public class ItemByMass implements Item {

    @Id
    private ObjectId id;
    private String unit;
    private String name;
    private double price;
    private double amount;

    /*
     * the amount after converting the current amount to gram (g)
     * */
    private double standardAmount;

    @Override
    public void standardizeAmount() {
        UnitList ul = new UnitList();
        double base = ul.get(unit);
        standardAmount = base * amount;


        /*
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
         */
    }

    public Item compareTo(Item i1, Item i2) {
        double item1BaseAmount = UnitList.unitToStandardAmountMap.get(i1.getUnit());
        double item2BaseAmount = UnitList.unitToStandardAmountMap.get(i2.getUnit());
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
