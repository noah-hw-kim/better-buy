package com.orrijoa.ValueComparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

/*
* this map is used to convert regular metric/US unit to qudt unit system
* */

@Document
public class UnitMap {
    @Autowired
    HashMap<String, String> unitReferenceMap;

    @Autowired
    HashMap<String, String> unitTypeMap;

    public UnitMap() {
        unitReferenceMap = new HashMap<>();

        /*
        * volume convert to use external library
        * */
        unitReferenceMap.put("litter", "Qudt.Units.L");
        unitReferenceMap.put("milliliter", "Qudt.Units.MilliL");
        unitReferenceMap.put("gallon", "Qudt.Units.GAL_US");
        unitReferenceMap.put("quart", "Qudt.Units.QT_US");
        unitReferenceMap.put("pint", "Qudt.Units.PINT_US");
        unitReferenceMap.put("fluid ounces", "Qudt.Units.OZ_VOL_US");

        /*
        * mass convert to use external library
        * */
        unitReferenceMap.put("kilogram", "Qudt.Units.KiloGM");
        unitReferenceMap.put("gram", "Qudt.Units.GM");
        unitReferenceMap.put("milligram", "MilliGM");
        unitReferenceMap.put("pound", "Qudt.Units.LB");
        unitReferenceMap.put("ounce", "Qudt.Units.OZ");

        /*
         * length convert to use external library
         * */
        unitReferenceMap.put("kilometer", "Qudt.Units.KiloM");
        unitReferenceMap.put("meter", "Qudt.Units.M");
        unitReferenceMap.put("centimeter", "Qudt.Units.CentiM");
        unitReferenceMap.put("millimeter", "Qudt.Units.MilliM");
        unitReferenceMap.put("mile", "Qudt.Units.MI_US");
        unitReferenceMap.put("yard", "Qudt.Units.YD");
        unitReferenceMap.put("foot", "Qudt.Units.FT");
        unitReferenceMap.put("inch", "Qudt.Units.IN");


        /*
        * reference for the frontend. It will be loaded to show the list
        * */
        unitTypeMap.put("volume", "gallon");
        unitTypeMap.put("volume", "quart");
        unitTypeMap.put("volume", "pint");
        unitTypeMap.put("volume", "fluid ounces");
        unitTypeMap.put("volume", "gallon");
        unitTypeMap.put("volume", "litter");
        unitTypeMap.put("volume", "milliliter");

        unitTypeMap.put("mass", "pound");
        unitTypeMap.put("mass", "ounce");
        unitTypeMap.put("mass", "kilogram");
        unitTypeMap.put("mass", "gram ounces");
        unitTypeMap.put("mass", "milligram");

        unitTypeMap.put("length", "kilometer");
        unitTypeMap.put("length", "meter");
        unitTypeMap.put("length", "centimeter");
        unitTypeMap.put("length", "millimeter");
        unitTypeMap.put("length", "mile");
        unitTypeMap.put("length", "yard");
        unitTypeMap.put("length", "foot");
        unitTypeMap.put("length", "inch");
    }
}
