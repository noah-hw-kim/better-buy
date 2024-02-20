package com.orrijoa.BetterBuy.models;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

/*
* Returns data used to populate Unit Type and Unit dropdowns
* */
@Data
public class UnitList {

    private Map<String, Set<String>> unitTypeToUnit;

    public UnitList() {
        /*
         * reference for the frontend. It will be loaded to show the list
         * */
        Set<String> volumeUnitSet = new HashSet<>();
        Set<String> massUnitSet = new HashSet<>();
        Set<String> lengthUnitSet = new HashSet<>();
        unitTypeToUnit = new HashMap<>();

        Collections.addAll(volumeUnitSet, "us liquid gallon", "us liquid quart", "us liquid pint", "us legal cup", "us fluid ounces", "liter", "milliliter");
        Collections.addAll(massUnitSet, "pound", "mass ounces", "kilogram", "gram", "milligram");
        Collections.addAll(lengthUnitSet, "kilometer", "meter", "centimeter", "millimeter", "mile", "yard", "foot", "inch");

        unitTypeToUnit.put("volume", volumeUnitSet);
        unitTypeToUnit.put("mass", massUnitSet);
        unitTypeToUnit.put("length", lengthUnitSet);
    }

    public Map<String, Set<String>> getUnitTypeToUnit() {
        return unitTypeToUnit;
    }
}
