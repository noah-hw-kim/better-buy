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
        return new ResponseEntity<List<Item>>(valueComparerService.getSearchedItems(text), HttpStatus.OK);
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

    // get Comparison obj that stores compared items and determines best value item
    @GetMapping("/item-comparison/{itemIdList}")
    public ResponseEntity<Comparison> getBestValue(@PathVariable String[] itemIdList) {
        return new ResponseEntity<Comparison>(valueComparerService.getBestValue(itemIdList), HttpStatus.OK);
    }


    /*
     * Backend Test Methods -----------------------------------------------------------------------------------------------
     * */

    // used for swagger api to test CRUD methods on backend
    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/");
    }
}
