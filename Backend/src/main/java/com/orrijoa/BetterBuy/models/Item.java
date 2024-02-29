package com.orrijoa.BetterBuy.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotBlank(message = "Invalid Unit: Empty unit")
    @NotNull(message = "Invalid Unit: Unit is NULL")
    private String unit;

    @NotBlank(message = "Invalid Price: Empty Price")
    @NotNull(message = "Invalid Price: Price is NULL")
    @Min(value = 0, message = "Invalid Price: Less than zero")
    private double price;

    @NotBlank(message = "Invalid Amount: Empty Amount")
    @NotNull(message = "Invalid Amount: Amount is NULL")
    @Min(value = 0, message = "Invalid Amount: Less than zero")
    private double amount;

    @NotBlank(message = "Invalid Name: Empty Name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 1, max = 30, message = "Invalid Name: Must be of 1 - 30 characters")
    private String name;

    @NotBlank(message = "Invalid Brand: Empty Brand")
    @NotNull(message = "Invalid Brand: Brand is NULL")
    @Size(min = 1, max = 30, message = "Invalid Brand: Must be of 1 - 30 characters")
    private String brand;

    @NotBlank(message = "Invalid Store: Empty Store")
    @NotNull(message = "Invalid Store: Store is NULL")
    @Size(min = 1, max = 30, message = "Invalid Store: Must be of 1 - 30 characters")
    private String store;

    @NotBlank(message = "Invalid Category: Empty Category")
    @NotNull(message = "Invalid Category: Category is NULL")
    @Size(min = 1, max = 30, message = "Invalid Category: Must be of 1 - 30 characters")
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
        result = prime * result + Integer.parseInt(id);
        result = prime * result + (int) price;
        result = prime * result + (int) amount;
        result = prime * result + (int) pricePerBaseUnit;

        return result;
    }
}
