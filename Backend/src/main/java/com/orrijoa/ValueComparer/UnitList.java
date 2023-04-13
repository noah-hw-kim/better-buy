package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import io.github.qudtlib.model.Unit;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Data
@Component
public class UnitList {

    public enum unit {
        US_LIQUID_GALLON,
        
    }

    private static Map<String, Double> unitToStandardAmountMap;
    private static Map<String, Set<String>> unitList;

    // represent a single unit
    private final BigDecimal ONE_UNIT = new BigDecimal("1");

    // base units for each quantity
    private final Unit BASE_VOLUME = Qudt.Units.OZ_VOL_US;
    private final Unit BASE_MASS = Qudt.Units.OZ;
    private final Unit BASE_LENGTH = Qudt.Units.IN;

    public UnitList() {
        /*
        * reference for the frontend. It will be loaded to show the list
        * */
        Set<String> volumeSet = new HashSet<>();
        Set<String> massSet = new HashSet<>();
        Set<String> lengthSet = new HashSet<>();
        unitList = new HashMap<>();

        volumeSet.add("us liquid gallon");
        volumeSet.add("us liquid quart");
        volumeSet.add("us liquid pint");
        volumeSet.add("us legal cup");
        volumeSet.add("us fluid ounces");
        volumeSet.add("liter");
        volumeSet.add("milliliter");

        massSet.add("pound");
        massSet.add("mass ounces");
        massSet.add("kilogram");
        massSet.add("gram");
        massSet.add("milligram");

        lengthSet.add("kilometer");
        lengthSet.add("meter");
        lengthSet.add("centimeter");
        lengthSet.add("millimeter");
        lengthSet.add("mile");
        lengthSet.add("yard");
        lengthSet.add("foot");
        lengthSet.add("inch");

        unitList.put("volume", volumeSet);
        unitList.put("mass", massSet);
        unitList.put("length", lengthSet);

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

        unitToStandardAmountMap.put("us liquid gallon", convertToBaseVolume(Qudt.Units.GAL_US));
        unitToStandardAmountMap.put("us liquid quart", convertToBaseVolume(Qudt.Units.QT_US));
        unitToStandardAmountMap.put("us liquid pint", convertToBaseVolume(Qudt.Units.PINT_US));
        unitToStandardAmountMap.put("us legal cup", convertToBaseVolume(Qudt.Units.CUP_US));
        unitToStandardAmountMap.put("us fluid ounces", convertToBaseVolume(Qudt.Units.OZ_VOL_US));
        unitToStandardAmountMap.put("liter", convertToBaseVolume(Qudt.Units.L));
        unitToStandardAmountMap.put("milliliter", convertToBaseVolume(Qudt.Units.MilliL));


        unitToStandardAmountMap.put("pound", convertToBaseMass(Qudt.Units.LB));
        unitToStandardAmountMap.put("mass ounces", convertToBaseMass(Qudt.Units.OZ));
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

    public Map<String, Set<String>> getUnitList() {
        return unitList;
    }
}
