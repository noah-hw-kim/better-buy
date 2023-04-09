package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import io.github.qudtlib.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;

/*
* Measurements Available:
*
*
* */

@Data
@AllArgsConstructor
public class UnitList {

    private static HashMap<String, Double> unitToStandardAmountMap;

    private HashSet<String> volumeSet;

    private HashSet<String> massSet;

    private HashSet<String> lengthSet;

    // represent a single unit
    private final BigDecimal ONE_UNIT = new BigDecimal("1");

    private final Unit BASE_VOLUME = Qudt.Units.MilliL;
    private final Unit BASE_MASS = Qudt.Units.GM;
    private final Unit BASE_LENGTH = Qudt.Units.M;

    public UnitList() {
        /*
        * reference for the frontend. It will be loaded to show the list
        * */
        volumeSet = new HashSet<>();
        massSet = new HashSet<>();
        lengthSet = new HashSet<>();

        volumeSet.add("gallon");
        volumeSet.add("quart");
        volumeSet.add("pint");
        volumeSet.add("fluid ounces");
        volumeSet.add("litter");
        volumeSet.add("milliliter");

        massSet.add("pound");
        massSet.add("gram ounces");
        massSet.add("kilogram");
        massSet.add("milligram");

        lengthSet.add("kilometer");
        lengthSet.add("meter");
        lengthSet.add("centimeter");
        lengthSet.add("millimeter");
        lengthSet.add("mile");
        lengthSet.add("yard");
        lengthSet.add("foot");
        lengthSet.add("inch");

        /*
        * 1 unit : convert the current 1 unit to the base unit
        * 1) volume: base unit - milliliter (ml)
        * 2) mass: base unit - gram (g)
        * 3) length: base unit - meter (m)
        *
        * e.g.)
        * "gallon" : convert 1 gallon to milliliter (ml)
        * "pound" : convert 1 pound to gram (g)
        * "mile" : convert 1 mile to meter (m)
        * */
        unitToStandardAmountMap = new HashMap<>();

        unitToStandardAmountMap.put("gallon", convertToBaseVolume(Qudt.Units.GAL_US));
        unitToStandardAmountMap.put("quart", convertToBaseVolume(Qudt.Units.QT_US));
        unitToStandardAmountMap.put("pint", convertToBaseVolume(Qudt.Units.PINT_US));
        unitToStandardAmountMap.put("fluid ounces", convertToBaseVolume(Qudt.Units.OZ_VOL_US));
        unitToStandardAmountMap.put("liter", convertToBaseVolume(Qudt.Units.L));
        unitToStandardAmountMap.put("milliliter", convertToBaseVolume(Qudt.Units.MilliL));


        unitToStandardAmountMap.put("pound", convertToBaseMass(Qudt.Units.LB));
        unitToStandardAmountMap.put("gram ounces", convertToBaseMass(Qudt.Units.OZ));
        unitToStandardAmountMap.put("kilogram", convertToBaseMass(Qudt.Units.KiloGM));
        unitToStandardAmountMap.put("gram", convertToBaseMass(Qudt.Units.GM));
        unitToStandardAmountMap.put("milligram", convertToBaseMass(Qudt.Units.MilliGM));


        unitToStandardAmountMap.put("kilometer", convertToBaseLength(Qudt.Units.KiloM));
        unitToStandardAmountMap.put("meter", convertToBaseLength(Qudt.Units.M));
        unitToStandardAmountMap.put("centimeter", convertToBaseLength(Qudt.Units.CentiM));
        unitToStandardAmountMap.put("millimeter", convertToBaseLength(Qudt.Units.MilliM));
        unitToStandardAmountMap.put("mile", convertToBaseLength(Qudt.Units.MI_US));
        unitToStandardAmountMap.put("yard", convertToBaseLength(Qudt.Units.YD));
        unitToStandardAmountMap.put("foot", convertToBaseLength(Qudt.Units.FT));
        unitToStandardAmountMap.put("inch", convertToBaseLength(Qudt.Units.IN));
    }

    private Double convertToBaseVolume(Unit unit1) {
        return Qudt.convert(ONE_UNIT, unit1, BASE_VOLUME).doubleValue();
    }

    private Double convertToBaseMass(Unit unit1) {
        return Qudt.convert(ONE_UNIT, unit1, BASE_MASS).doubleValue();
    }

    private Double convertToBaseLength(Unit unit1) {
        return Qudt.convert(ONE_UNIT, unit1, BASE_LENGTH).doubleValue();
    }

    public static Double get(String unit) {
        return unitToStandardAmountMap.get(unit);
    }
}