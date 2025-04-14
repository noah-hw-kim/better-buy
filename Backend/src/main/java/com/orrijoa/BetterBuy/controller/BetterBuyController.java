package com.orrijoa.BetterBuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orrijoa.BetterBuy.models.Categories;
import com.orrijoa.BetterBuy.models.Comparison;
import com.orrijoa.BetterBuy.models.Item;
import com.orrijoa.BetterBuy.models.UnitList;
import com.orrijoa.BetterBuy.service.BetterBuyService;

@RestController
@RequestMapping("api/better-buy")
@CrossOrigin(origins = "https://better-buy-frontend.onrender.com")
public class BetterBuyController {

    @Autowired
    private BetterBuyService betterBuyService;


    /*
    * Methods for item CRUD operations -----------------------------------------------------------------------------------------------
    * */

    // add new items in mongoDB
    @PostMapping("/items")
    public ResponseEntity<List<Item>> createItems(@RequestBody List<Item> items) {
        return new ResponseEntity<List<Item>>(betterBuyService.createItems(items), HttpStatus.CREATED);
    }

    // delete a single item in mongoDB
    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable String itemId) {
        betterBuyService.deleteItem(itemId);
    }

    // delete all items in mongoDB
    @DeleteMapping("/items")
    public void deleteItems() {
        betterBuyService.deleteAllItems();
    }

    // get all items in mongoDB
    @GetMapping("/all-items")
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<List<Item>>(betterBuyService.getAllItems(),HttpStatus.OK);
    }

    // get searched items in mongoDB
    @GetMapping("/items/{text}")
    public ResponseEntity<List<Item>> getItems(@PathVariable String text) {
        return new ResponseEntity<List<Item>>(betterBuyService.getSearchedItems(text), HttpStatus.OK);
    }

    // get item category lists from the "models" package
    @GetMapping("/categories")
    public ResponseEntity<Categories> getCategories() {
        return new ResponseEntity<Categories>(betterBuyService.getCategories(), HttpStatus.OK);
    }

    // get unit lists from the "models" package
    @GetMapping("/unit-list")
    public ResponseEntity<UnitList> getUnitList() {
        return new ResponseEntity<UnitList>(betterBuyService.getUnitList(), HttpStatus.OK);
    }

    // get Comparison obj that stores compared items and determines best value item
    @GetMapping("/item-comparison/{itemIdList}")
    public ResponseEntity<Comparison> getBestValue(@PathVariable String[] itemIdList) {
        return new ResponseEntity<Comparison>(betterBuyService.getBestValue(itemIdList), HttpStatus.OK);
    }


    /*
     * Backend Test Methods -----------------------------------------------------------------------------------------------
     * */

    // used for swagger api to test CRUD methods on backend
    // @ApiIgnore
    // @RequestMapping(value = "/")
    // public void redirect(HttpServletResponse response) throws IOException {
    //     response.sendRedirect("/swagger-ui/");
    // }
}
