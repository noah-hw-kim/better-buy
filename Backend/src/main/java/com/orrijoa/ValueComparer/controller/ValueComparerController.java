package com.orrijoa.ValueComparer.controller;

import com.orrijoa.ValueComparer.service.ValueComparerService;
import com.orrijoa.ValueComparer.models.Categories;
import com.orrijoa.ValueComparer.models.Comparison;
import com.orrijoa.ValueComparer.models.Item;
import com.orrijoa.ValueComparer.models.UnitList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/value-comparer")
@CrossOrigin
public class ValueComparerController {

    @Autowired
    private ValueComparerService valueComparerService;

    /*
    * Methods for item CRUD operations -----------------------------------------------------------------------------------------------
    * */
    // add new items in mongoDB
    @PostMapping("/items")
    public ResponseEntity<List<Item>> createItems(@RequestBody List<Item> items) {
        return new ResponseEntity<List<Item>>(valueComparerService.createItems(items), HttpStatus.CREATED);
    }

    // delete a single item in mongoDB
    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable String itemId) {
        valueComparerService.deleteItem(itemId);
    }

    // delete all items in mongoDB
    @DeleteMapping("/items")
    public void deleteItems() {
        valueComparerService.deleteAllItems();
    }

    // get all items in mongoDB
    @GetMapping("/all-items")
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<List<Item>>(valueComparerService.getAllItems(),HttpStatus.OK);
    }

    // get searched items in mongoDB
    @GetMapping("/items/{text}")
    public ResponseEntity<List<Item>> getItems(@PathVariable String text) {
        return new ResponseEntity<List<Item>>(valueComparerService.getItems(text), HttpStatus.OK);
    }

    // get item category lists from the "models" package
    @GetMapping("/categories")
    public ResponseEntity<Categories> getCategories() {
        return new ResponseEntity<Categories>(valueComparerService.getCategories(), HttpStatus.OK);
    }

    // get unit lists from the "models" package
    @GetMapping("/unit-list")
    public ResponseEntity<UnitList> getUnitList() {
        return new ResponseEntity<UnitList>(valueComparerService.getUnitList(), HttpStatus.OK);
    }

    // get Comparison obj that shows compared item list, best value item, and how much the best value item is cheaper
    @GetMapping("/item-comparison/{itemIdList}")
    public ResponseEntity<Comparison> getCheaper(@PathVariable String[] itemIdList) {
        return new ResponseEntity<Comparison>(valueComparerService.getCheaper(itemIdList), HttpStatus.OK);
    }

    // used for swagger api to test
    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/");
    }


    //    @GetMapping("/item-find/{itemName}")
//    public ResponseEntity<List<Item>> getItemsWithName(@PathVariable String itemName) {
//        return new ResponseEntity<List<Item>>(valueComparerService.getItemsWithName(itemName), HttpStatus.OK);
//    }

    /*
    @GetMapping("/item1id/{item1id}/item2id/{item2id}")
    public ResponseEntity<Comparison> getCheaper(@PathVariable long item1id, @PathVariable long item2id) {
        return new ResponseEntity<Comparison>(valueComparerService.getCheaper(item1id, item2id), HttpStatus.OK);
    }

    @GetMapping("/item1id/{item1id}/item2id/{item2id}/item3id/{item3id}")
    public ResponseEntity<Comparison> getCheaper(@PathVariable long item1id, @PathVariable long item2id, @PathVariable long item3id) {
        return new ResponseEntity<Comparison>(valueComparerService.getCheaper(item1id, item2id, item3id), HttpStatus.OK);
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

    //    @GetMapping("/items/category/{category}/sub-category/{subCategory}")
//    public ResponseEntity<List<Item>> getItemsByCateogryAndSubCategory(@PathVariable String category, @PathVariable String subCategory) {
//        return new ResponseEntity<List<Item>>(valueComparerService.getItemsByCategoryAndSubCategory(category, subCategory), HttpStatus.OK);
//    }

    @PostMapping("/create/unit/{unit}/name/{name}/price/{price}/amount/{amount}/brand/{brand}/store/{store}/category/{category}/sub-category/{subCategory}")
    public ResponseEntity<Item> createItem(@PathVariable String unit, @PathVariable double price, @PathVariable double amount, @PathVariable String name, @PathVariable String brand, @PathVariable String store, @PathVariable String category, @PathVariable String subCategory) {
        return new ResponseEntity<Item>(valueComparerService.createItem(unit, price, amount, name, brand, store, category, subCategory), HttpStatus.CREATED);
    }

    @PostMapping("/new-item")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return new ResponseEntity<Item>(valueComparerService.createItem(item), HttpStatus.CREATED);
    }
    */
}
