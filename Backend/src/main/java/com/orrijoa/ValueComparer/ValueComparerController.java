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
    public ResponseEntity<Comparison> getCheaper(@PathVariable String unit1, @PathVariable double price1, @PathVariable double amount1, @PathVariable String unit2, @PathVariable double price2, @PathVariable double amount2) {
        return new ResponseEntity<Comparison>(valueComparerService.getCheaper(unit1, price1, amount1, unit2, price2, amount2), HttpStatus.OK);
    }

    @GetMapping("/unit1/{unit1}/price1/{price1}/amount1/{amount1}/unit2/{unit2}/price2/{price2}/amount2/{amount2}/unit3/{unit3}/price3/{price3}/amount3/{amount3}")
    public ResponseEntity<Comparison> getCheaper(@PathVariable String unit1, @PathVariable double price1, @PathVariable double amount1, @PathVariable String unit2, @PathVariable double price2, @PathVariable double amount2, @PathVariable String unit3, @PathVariable double price3, @PathVariable double amount3) {
        return new ResponseEntity<Comparison>(valueComparerService.getCheaper(unit1, price1, amount1, unit2, price2, amount2, unit3, price3, amount3), HttpStatus.OK);
    }

    @GetMapping("/unit1/{unit1}/price1/{price1}/amount1/{amount1}/unit2/{unit2}/price2/{price2}/amount2/{amount2}/unit3/{unit3}/price3/{price3}/amount3/{amount3}/unit4/{unit4}/price4/{price4}/amount4/{amount4}")
    public ResponseEntity<Comparison> getCheaper(@PathVariable String unit1, @PathVariable double price1, @PathVariable double amount1, @PathVariable String unit2, @PathVariable double price2, @PathVariable double amount2, @PathVariable String unit3, @PathVariable double price3, @PathVariable double amount3, @PathVariable String unit4, @PathVariable double price4, @PathVariable double amount4) {
        return new ResponseEntity<Comparison>(valueComparerService.getCheaper(unit1, price1, amount1, unit2, price2, amount2, unit3, price3, amount3, unit4, price4, amount4), HttpStatus.OK);
    }

    @GetMapping("/unit1/{unit1}/price1/{price1}/amount1/{amount1}/unit2/{unit2}/price2/{price2}/amount2/{amount2}/unit3/{unit3}/price3/{price3}/amount3/{amount3}/unit4/{unit4}/price4/{price4}/amount4/{amount4}/unit5/{unit5}/price5/{price5}/amount5/{amount5}")
    public ResponseEntity<Comparison> getCheaper(@PathVariable String unit1, @PathVariable double price1, @PathVariable double amount1, @PathVariable String unit2, @PathVariable double price2, @PathVariable double amount2, @PathVariable String unit3, @PathVariable double price3, @PathVariable double amount3, @PathVariable String unit4, @PathVariable double price4, @PathVariable double amount4, @PathVariable String unit5, @PathVariable double price5, @PathVariable double amount5) {
        return new ResponseEntity<Comparison>(valueComparerService.getCheaper(unit1, price1, amount1, unit2, price2, amount2, unit3, price3, amount3, unit4, price4, amount4, unit5, price5, amount5), HttpStatus.OK);
    }



//    @GetMapping("/unit1/{unit1}/price1/{price1}/amount1/{amount1}/unit2/{unit2}/price2/{price2}/amount2/{amount2}")
//    public ResponseEntity<Item> getCheaper(@PathVariable String unit1, @PathVariable double price1, @PathVariable double amount1, @PathVariable String unit2, @PathVariable double price2, @PathVariable double amount2) {
//        return new ResponseEntity<Item>(valueComparerService.getCheaper(unit1, price1, amount1, unit2, price2, amount2), HttpStatus.OK);
//    }
}
