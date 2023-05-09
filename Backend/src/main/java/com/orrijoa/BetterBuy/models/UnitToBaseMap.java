package com.orrijoa.BetterBuy.models;

import io.github.qudtlib.Qudt;
import io.github.qudtlib.model.Unit;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/*
* unitToBaseUnitRatio
* Object standardizing unit amounts to their base unit equivalent amount.
 * Base Volume Unit: us fluid ounces (vol oz)
 * Base Mass Unit: mass ounces (oz)
 * Base length Unit: inches (in)
 *
 * e.g) 1 gallon :  128 fluid oz
 *
 * baseUnitToUnits
 * Per unit type, maps appropriate base unit to all other units of that unit type
 *
 * e.g) us fluid ounces : {us liquid gallon, us liquid quart, us liquid pint, us legal cup, us fluid ounces, liter, milliliter}
* */

@Data
public class UnitToBaseMap {
    private Map<String, Double> unitToBaseUnitRatio;
    private Map<String, Set<String>> baseUnitToUnits;

    // represents a single unit
    private static final BigDecimal ONE_UNIT = new BigDecimal("1");

    // base units for each unit type
    private static final Unit BASE_VOLUME = Qudt.Units.OZ_VOL_US;
    private static final Unit BASE_MASS = Qudt.Units.OZ;
    private static final Unit BASE_LENGTH = Qudt.Units.IN;

    public UnitToBaseMap() {
        UnitList unitList = new UnitList();
        unitToBaseUnitRatio = new HashMap<>();
        baseUnitToUnits = new HashMap<>();

        // volume units
        unitToBaseUnitRatio.put("us liquid gallon", convertToBaseVolume(Qudt.Units.GAL_US));
        unitToBaseUnitRatio.put("us liquid quart", convertToBaseVolume(Qudt.Units.QT_US));
        unitToBaseUnitRatio.put("us liquid pint", convertToBaseVolume(Qudt.Units.PINT_US));
        unitToBaseUnitRatio.put("us legal cup", convertToBaseVolume(Qudt.Units.CUP_US));
        unitToBaseUnitRatio.put("us fluid ounces", convertToBaseVolume(Qudt.Units.OZ_VOL_US));
        unitToBaseUnitRatio.put("liter", convertToBaseVolume(Qudt.Units.L));
        unitToBaseUnitRatio.put("milliliter", convertToBaseVolume(Qudt.Units.MilliL));

        // mass units
        unitToBaseUnitRatio.put("pound", convertToBaseMass(Qudt.Units.LB));
        unitToBaseUnitRatio.put("mass ounces", convertToBaseMass(Qudt.Units.OZ));
        unitToBaseUnitRatio.put("kilogram", convertToBaseMass(Qudt.Units.KiloGM));
        unitToBaseUnitRatio.put("gram", convertToBaseMass(Qudt.Units.GM));
        unitToBaseUnitRatio.put("milligram", convertToBaseMass(Qudt.Units.MilliGM));

        // length units
        unitToBaseUnitRatio.put("kilometer", convertToBaseLength(Qudt.Units.KiloM));
        unitToBaseUnitRatio.put("meter", convertToBaseLength(Qudt.Units.M));
        unitToBaseUnitRatio.put("centimeter", convertToBaseLength(Qudt.Units.CentiM));
        unitToBaseUnitRatio.put("millimeter", convertToBaseLength(Qudt.Units.MilliM));
        unitToBaseUnitRatio.put("mile", convertToBaseLength(Qudt.Units.MI_US));
        unitToBaseUnitRatio.put("yard", convertToBaseLength(Qudt.Units.YD));
        unitToBaseUnitRatio.put("foot", convertToBaseLength(Qudt.Units.FT));
        unitToBaseUnitRatio.put("inch", convertToBaseLength(Qudt.Units.IN));

        // base units
        baseUnitToUnits.put("us fluid ounces", unitList.getUnitTypeToUnit().get("volume"));
        baseUnitToUnits.put("mass ounces", unitList.getUnitTypeToUnit().get("mass"));
        baseUnitToUnits.put("inch", unitList.getUnitTypeToUnit().get("length"));
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
    public Double getBaseAmount(String unit) {
        return unitToBaseUnitRatio.get(unit);
    }

}
