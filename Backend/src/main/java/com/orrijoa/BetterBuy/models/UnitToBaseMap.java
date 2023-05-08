package com.orrijoa.BetterBuy.models;

import io.github.qudtlib.Qudt;
import io.github.qudtlib.model.Unit;
import lombok.Data;
import java.math.BigDecimal;
import java.util.*;

/*
* Object standardizing unit amounts to their base unit equivalent amount.
 * Base Volume Unit: us fluid ounces (vol oz)
 * Base Mass Unit: mass ounces (oz)
 * Base length Unit: inches (in)
 *
 * e.g) 1 gallon :  128 fluid oz
* */

@Data
public class UnitToBaseMap {
    private static Map<String, Double> unitToBase;

    // represents a single unit
    private static final BigDecimal ONE_UNIT = new BigDecimal("1");

    // base units for each unit type
    private static final Unit BASE_VOLUME = Qudt.Units.OZ_VOL_US;
    private static final Unit BASE_MASS = Qudt.Units.OZ;
    private static final Unit BASE_LENGTH = Qudt.Units.IN;

    public UnitToBaseMap() {
        unitToBase = new HashMap<>();

        // volume units
        unitToBase.put("us liquid gallon", convertToBaseVolume(Qudt.Units.GAL_US));
        unitToBase.put("us liquid quart", convertToBaseVolume(Qudt.Units.QT_US));
        unitToBase.put("us liquid pint", convertToBaseVolume(Qudt.Units.PINT_US));
        unitToBase.put("us legal cup", convertToBaseVolume(Qudt.Units.CUP_US));
        unitToBase.put("us fluid ounces", convertToBaseVolume(Qudt.Units.OZ_VOL_US));
        unitToBase.put("liter", convertToBaseVolume(Qudt.Units.L));
        unitToBase.put("milliliter", convertToBaseVolume(Qudt.Units.MilliL));

        // mass units
        unitToBase.put("pound", convertToBaseMass(Qudt.Units.LB));
        unitToBase.put("mass ounces", convertToBaseMass(Qudt.Units.OZ));
        unitToBase.put("kilogram", convertToBaseMass(Qudt.Units.KiloGM));
        unitToBase.put("gram", convertToBaseMass(Qudt.Units.GM));
        unitToBase.put("milligram", convertToBaseMass(Qudt.Units.MilliGM));

        // length units
        unitToBase.put("kilometer", convertToBaseLength(Qudt.Units.KiloM));
        unitToBase.put("meter", convertToBaseLength(Qudt.Units.M));
        unitToBase.put("centimeter", convertToBaseLength(Qudt.Units.CentiM));
        unitToBase.put("millimeter", convertToBaseLength(Qudt.Units.MilliM));
        unitToBase.put("mile", convertToBaseLength(Qudt.Units.MI_US));
        unitToBase.put("yard", convertToBaseLength(Qudt.Units.YD));
        unitToBase.put("foot", convertToBaseLength(Qudt.Units.FT));
        unitToBase.put("inch", convertToBaseLength(Qudt.Units.IN));
    }

    // unitToBaseMap helper method: Converts each volume type unit to its base unit equivalent
    private Double convertToBaseVolume(Unit unit1) {
        return Qudt.convert(ONE_UNIT, unit1, BASE_VOLUME).doubleValue();
    }

    // unitToBaseMap helper method: Converts each mass type unit to its base unit equivalent
    private Double convertToBaseMass(Unit unit1) {
        return Qudt.convert(ONE_UNIT, unit1, BASE_MASS).doubleValue();
    }

    // unitToBaseMap helper method: Converts each length type unit to its base unit equivalent
    private Double convertToBaseLength(Unit unit1) {
        return Qudt.convert(ONE_UNIT, unit1, BASE_LENGTH).doubleValue();
    }

    // return base unit equivalent amount
    public static Double getBaseAmount(String unit) {
        return unitToBase.get(unit);
    }
}
