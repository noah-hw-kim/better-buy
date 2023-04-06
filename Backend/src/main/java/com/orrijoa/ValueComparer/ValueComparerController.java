package com.orrijoa.ValueComparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/value-comparer")
public class ValueComparerController {

    @Autowired
    ValueComparerService valueComparerService;

    @GetMapping("/value/amount1/{amount1}/unit1/{unit1}/price1/{price1}/amount2/{amount2}/unit2/{unit2}/price2/{price2}")
    public ResponseEntity<Item> getCheaper(@PathVariable Double amount1, @PathVariable String unit1, @PathVariable Double price1, @PathVariable Double amount2, @PathVariable String unit2, @PathVariable Double price2) {
        return new ResponseEntity<Item>(valueComparerService.getCheaper(amount1, unit1, price1, amount2, unit2, price2), HttpStatus.OK);
    }
}
