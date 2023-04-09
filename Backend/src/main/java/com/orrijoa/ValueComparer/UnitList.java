package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import io.github.qudtlib.model.QuantityValue;
import io.github.qudtlib.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;

/*
* this map is used to convert regular metric/US unit to qudt unit system
* */

@Data
@AllArgsConstructor
public class UnitList {

    private HashMap<String, Double> unitToStandardAmountMap;

    HashSet<String> volumeSet;

    HashSet<String> massSet;

    HashSet<String> lengthSet;

    // represent a single unit
    static BigDecimal ONE_UNIT = new BigDecimal("1");

    final Unit BASE_VOLUME = Qudt.Units.MilliL;
    final Unit BASE_MASS = Qudt.Units.GM;
    final Unit BASE_LENGTH = Qudt.Units.M;

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
        * unit: convert 1 unit to the base unit
        * e.g.) "gallon" : convert 1 gallon to milliliter
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

        /*
        unitToStandardAmountMap.put("gallon", Qudt.convert(new BigDecimal("1"), Qudt.Units.GAL_US, Qudt.Units.MilliL).doubleValue());
        unitToStandardAmountMap.put("quart", Qudt.convert(new BigDecimal("1"), Qudt.Units.QT_US, Qudt.Units.MilliL).doubleValue());
        unitToStandardAmountMap.put("pint", Qudt.convert(new BigDecimal("1"), Qudt.Units.PINT_US, Qudt.Units.MilliL).doubleValue());
        unitToStandardAmountMap.put("fluid ounces", Qudt.convert(new BigDecimal("1"), Qudt.Units.OZ_VOL_US, Qudt.Units.MilliL).doubleValue());
        unitToStandardAmountMap.put("litter", Qudt.convert(new BigDecimal("1"), Qudt.Units.L, Qudt.Units.MilliL).doubleValue());
        unitToStandardAmountMap.put("milliliter", Qudt.convert(new BigDecimal("1"), Qudt.Units.MilliL, Qudt.Units.MilliL).doubleValue());

        unitToStandardAmountMap.put("pound", Qudt.convert(new BigDecimal("1"), Qudt.Units.LB, Qudt.Units.GM).doubleValue());
        unitToStandardAmountMap.put("gram ounces", Qudt.convert(new BigDecimal("1"), Qudt.Units.OZ, Qudt.Units.GM).doubleValue());
        unitToStandardAmountMap.put("kilogram", Qudt.convert(new BigDecimal("1"), Qudt.Units.KiloGM, Qudt.Units.GM).doubleValue());
        unitToStandardAmountMap.put("gram", Qudt.convert(new BigDecimal("1"), Qudt.Units.GM, Qudt.Units.GM).doubleValue());
        unitToStandardAmountMap.put("milligram", Qudt.convert(new BigDecimal("1"), Qudt.Units.MilliL, Qudt.Units.GM).doubleValue());

        unitToStandardAmountMap.put("kilometer", Qudt.convert(new BigDecimal("1"), Qudt.Units.KiloM, Qudt.Units.M).doubleValue());
        unitToStandardAmountMap.put("meter", Qudt.convert(new BigDecimal("1"), Qudt.Units.M, Qudt.Units.M).doubleValue());
        unitToStandardAmountMap.put("centimeter", Qudt.convert(new BigDecimal("1"), Qudt.Units.CentiM, Qudt.Units.M).doubleValue());
        unitToStandardAmountMap.put("millimeter", Qudt.convert(new BigDecimal("1"), Qudt.Units.MilliM, Qudt.Units.M).doubleValue());
        unitToStandardAmountMap.put("mile", Qudt.convert(new BigDecimal("1"), Qudt.Units.MilliL, Qudt.Units.MI_US).doubleValue());
        unitToStandardAmountMap.put("yard", Qudt.convert(new BigDecimal("1"), Qudt.Units.KiloGM, Qudt.Units.YD).doubleValue());
        unitToStandardAmountMap.put("foot", Qudt.convert(new BigDecimal("1"), Qudt.Units.GM, Qudt.Units.FT).doubleValue());
        unitToStandardAmountMap.put("inch", Qudt.convert(new BigDecimal("1"), Qudt.Units.MilliL, Qudt.Units.IN).doubleValue());
         */
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

    public Double get (String unit) {
        return unitToStandardAmountMap.get(unit);
    }
}
