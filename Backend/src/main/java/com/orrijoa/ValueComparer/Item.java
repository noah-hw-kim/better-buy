package com.orrijoa.ValueComparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item implements Comparable<Item>{
    @Id
    private ObjectId id;
    private String unit;
    private String name;
    private double price;
    private double amount;


    @Override
    public int compareTo(Item o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }
}
