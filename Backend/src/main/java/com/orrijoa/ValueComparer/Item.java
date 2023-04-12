package com.orrijoa.ValueComparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "items")
public class Item implements Comparable<Item> {

    @Id
    private ObjectId id;
    private String unit;
    private double price;
    private double amount;
    private String name;
    private String brand;
    private String store;
    private String category;


    public Item(String unit, double price, double amount) {
        this.unit = unit;
        this.price = price;
        this.amount = amount;
    }

    public Item(String unit, double price, double amount, String name, String brand, String store, String category) {
        this.unit = unit;
        this.price = price;
        this.amount = amount;
        this.name = name;
        this.brand = brand;
        this.store = store;
        this.category = category;
    }

    public double getPricePerBaseAmount() {
        // amount that converted to the base unit
        double baseAmount = UnitList.get(unit) * amount;
        return price / baseAmount;
    }

    @Override
    public int compareTo(Item other) {
        double currentItemPricePerAmount = getPricePerBaseAmount();
        double otherItemPricePerAmount = other.getPricePerBaseAmount();

//        compareTo method calculates each of the itemPricePerAmount and return
//        - positive number. if i2 is cheaper than i1
//        - 0. if i1 and i2 has same value
//        - negative number. if i1 is cheaper than i2
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
