package com.orrijoa.ValueComparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/value-comparer")
@CrossOrigin
public class ValueComparerController {

    @Autowired
    ValueComparerService valueComparerService;

    @GetMapping("/unit-list")
    public ResponseEntity<UnitList> getUnitList() {
        return new ResponseEntity<UnitList>(valueComparerService.getList(), HttpStatus.OK);
    }

    @GetMapping("/unit1/{unit1}/price1/{price1}/amount1/{amount1}/unit2/{unit2}/price2/{price2}/amount2/{amount2}")
    public ResponseEntity<Item> getCheaper(@PathVariable String unit1, @PathVariable double price1, @PathVariable double amount1, @PathVariable String unit2, @PathVariable double price2, @PathVariable double amount2) {
        return new ResponseEntity<Item>(valueComparerService.getCheaper(unit1, price1, amount1, unit2, price2, amount2), HttpStatus.OK);
    }
}
