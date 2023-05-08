package com.orrijoa.ValueComparer.models;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import java.util.*;
import java.util.stream.Collectors;

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

    @Bean
    public Map<String, Set<String>> getUnitTypeToUnitCopy() {
        Map<String, Set<String>> unitTypeToUnitCopy = unitTypeToUnit.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> Set.copyOf(e.getValue())));

        return unitTypeToUnitCopy;
    }
}
