package com.orrijoa.ValueComparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Unit {

    public enum Quantity {
        VOLUME,
        MASS,
        LENGTH
    }

    private Quantity Quantity;
    private String unitName;
    private String unitSymbol;
    private BigDecimal bigDecimal;
}
