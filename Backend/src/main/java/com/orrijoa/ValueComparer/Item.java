package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

public interface Item extends Comparable<Item> {
    /*
     * convert unit1 and unit2 to the metric unit
     * */
    void standardizeAmount();

    @Override
    public int compareTo(Item other);

    @Override
    public boolean equals(Object other);

}
