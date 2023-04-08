package com.orrijoa.ValueComparer;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashSet;

/*
* this map is used to convert regular metric/US unit to qudt unit system
* */

@Data
@AllArgsConstructor
public class UnitList {

    HashSet<String> volumeSet;

    HashSet<String> massSet;

    HashSet<String> lengthSet;

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
        // volume convert to use external library
        unitReferenceMap.put("litter", "Qudt.Units.L");
        unitReferenceMap.put("milliliter", "Qudt.Units.MilliL");
        unitReferenceMap.put("gallon", "Qudt.Units.GAL_US");
        unitReferenceMap.put("quart", "Qudt.Units.QT_US");
        unitReferenceMap.put("pint", "Qudt.Units.PINT_US");
        unitReferenceMap.put("fluid ounces", "Qudt.Units.OZ_VOL_US");

        // mass convert to use external library
        unitReferenceMap.put("kilogram", "Qudt.Units.KiloGM");
        unitReferenceMap.put("gram", "Qudt.Units.GM");
        unitReferenceMap.put("milligram", "MilliGM");
        unitReferenceMap.put("pound", "Qudt.Units.LB");
        unitReferenceMap.put("ounce", "Qudt.Units.OZ");

        // length convert to use external library
        unitReferenceMap.put("kilometer", "Qudt.Units.KiloM");
        unitReferenceMap.put("meter", "Qudt.Units.M");
        unitReferenceMap.put("centimeter", "Qudt.Units.CentiM");
        unitReferenceMap.put("millimeter", "Qudt.Units.MilliM");
        unitReferenceMap.put("mile", "Qudt.Units.MI_US");
        unitReferenceMap.put("yard", "Qudt.Units.YD");
        unitReferenceMap.put("foot", "Qudt.Units.FT");
        unitReferenceMap.put("inch", "Qudt.Units.IN");
         */
    }
}
