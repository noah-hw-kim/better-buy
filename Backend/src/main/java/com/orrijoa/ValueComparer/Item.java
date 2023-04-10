package com.orrijoa.ValueComparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

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

    public Item(String unit, double price, double amount) {
        this.unit = unit;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public int compareTo(Item other) {
        double currentItemBaseAmount = UnitList.get(unit) * amount;
        double otherItemBaseAmount = UnitList.get(other.getUnit()) * other.getAmount();

        double currentItemPricePerAmount = price / currentItemBaseAmount;
        double otherItemPricePerAmount = other.getPrice() / otherItemBaseAmount;

        return Double.compare(currentItemPricePerAmount, otherItemPricePerAmount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;

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
