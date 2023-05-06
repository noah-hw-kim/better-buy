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

    private Map<String, List<String>> unitList;

    public UnitList() {
        /*
         * reference for the frontend. It will be loaded to show the list
         * */
        List<String> volumeUnitList = new ArrayList<>();
        List<String> massUnitList = new ArrayList<>();
        List<String> lengthUnitList = new ArrayList<>();
        unitList = new HashMap<>();

        Collections.addAll(volumeUnitList, "us liquid gallon", "us liquid quart", "us liquid pint", "us legal cup", "us fluid ounces", "liter", "milliliter");
        Collections.addAll(massUnitList, "pound", "mass ounces", "kilogram", "gram", "milligram");
        Collections.addAll(lengthUnitList, "kilometer", "meter", "centimeter", "millimeter", "mile", "yard", "foot", "inch");

        unitList.put("volume", volumeUnitList);
        unitList.put("mass", massUnitList);
        unitList.put("length", lengthUnitList);
    }

    @Bean
    public Map<String, List<String>> getUnitList() {
        Map<String, List<String>> unitListCopy = unitList.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> List.copyOf(e.getValue())));

        return unitListCopy;
    }
}
