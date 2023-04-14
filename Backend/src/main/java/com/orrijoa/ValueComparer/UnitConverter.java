package com.orrijoa.ValueComparer;

import io.github.qudtlib.Qudt;
import io.github.qudtlib.model.Unit;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Data
@Component
public class UnitConverter {

    private static Map<String, Double> unitToStandardAmountMap;
    private static Map<String, List<String>> unitList;

    // represent a single unit
    private BigDecimal ONE_UNIT = new BigDecimal("1");

    // base units for each quantity
    private Unit BASE_VOLUME = Qudt.Units.OZ_VOL_US;
    private Unit BASE_MASS = Qudt.Units.OZ;
    private Unit BASE_LENGTH = Qudt.Units.IN;

    public UnitConverter() {
        /*
        * reference for the frontend. It will be loaded to show the list
        * */
        List<String> volumeUnitList = new ArrayList<>();
        List<String> massUnitList = new ArrayList<>();
        List<String> lengthUnitList = new ArrayList<>();
        unitList = new HashMap<>();

        volumeUnitList.add("us liquid gallon");
        volumeUnitList.add("us liquid quart");
        volumeUnitList.add("us liquid pint");
        volumeUnitList.add("us legal cup");
        volumeUnitList.add("us fluid ounces");
        volumeUnitList.add("liter");
        volumeUnitList.add("milliliter");

        massUnitList.add("pound");
        massUnitList.add("mass ounces");
        massUnitList.add("kilogram");
        massUnitList.add("gram");
        massUnitList.add("milligram");

        lengthUnitList.add("kilometer");
        lengthUnitList.add("meter");
        lengthUnitList.add("centimeter");
        lengthUnitList.add("millimeter");
        lengthUnitList.add("mile");
        lengthUnitList.add("yard");
        lengthUnitList.add("foot");
        lengthUnitList.add("inch");

        unitList.put("volume", volumeUnitList);
        unitList.put("mass", massUnitList);
        unitList.put("length", lengthUnitList);

        /*
        * 1 unit : convert the current 1 unit to the base unit
        * 1) volume: base unit - us fluid ounces (vol oz)
        * 2) mass: base unit - mass ounces (oz)
        * 3) length: base unit - inches (in)
        *
        * e.g.)
        * "gallon" : convert 1 gallon to us fluid ounces (vol oz)
        * "pound" : convert 1 pound to mass ounces (oz
        * "mile" : convert 1 mile to inches (in)
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

    public Map<String, List<String>> getUnitList() {
        return unitList;
    }
}
