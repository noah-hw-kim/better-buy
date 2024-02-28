package com.orrijoa.BetterBuy.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * represents a single record in ItemRepository
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "items")
public class Item implements Comparable<Item> {
    @Id
    private String id;
    private String unit;
    private double price;
    private double amount;
    private String name;
    private String brand;
    private String store;
    private String category;
    private double pricePerBaseUnit;  // e.g) 1 gallon :  128 fluid oz
    private String baseUnit;

    @Override
    public int compareTo(Item other) {
        double currPricePerBaseUnit = pricePerBaseUnit;
        double otherPricePerBaseUnit = other.getPricePerBaseUnit();
        return Double.compare(currPricePerBaseUnit, otherPricePerBaseUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;

        return other.id == id && other.unit.equals(unit) && other.name.equals(name) && other.price == price && other.amount == amount && other.brand.equals(brand) && other.store.equals(store) && other.category.equals(category) && other.pricePerBaseUnit == pricePerBaseUnit && other.baseUnit.equals(baseUnit);
    }

    // creating hashcode() with custom : starting point 1, prime multiplier 31
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((store == null) ? 0 : store.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((baseUnit == null) ? 0 : baseUnit.hashCode());
        result = prime * result + Integer.parseInt(id) ;
        result = prime * result + (int) price;
        result = prime * result + (int) amount;
        result = prime * result + (int) pricePerBaseUnit;

        return result;
    }
}
