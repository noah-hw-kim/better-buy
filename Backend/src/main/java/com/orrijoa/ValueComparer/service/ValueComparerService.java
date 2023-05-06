package com.orrijoa.ValueComparer.service;

import com.orrijoa.ValueComparer.models.*;
import com.orrijoa.ValueComparer.repository.ItemRepository;
import com.orrijoa.ValueComparer.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ValueComparerService {
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private SearchRepository searchRepo;
    @Autowired
    private UnitList unitList;
    @Autowired
    private Categories categories;
    @Autowired
    private UnitToBaseMap unitToBaseMap;
    private final double DECIMAL_PLACE = 1000.0;

    /*
    * Used for frontend set up ---------------------------------------------------
    * */

    public UnitList getUnitList() {
        return unitList;
    }

    public Categories getCategories() {
        return categories;
    }

    // return all records from ItemRepository
    public List<Item> getAllItems() {
        return itemRepo.findAll(Sort.by(Sort.Direction.ASC, "category").and(Sort.by(Sort.Direction.ASC, "pricePerBaseAmount")));
    }


    /*
     * Methods for item CRUD operations ---------------------------------------------------
     * */

    // save new items to ItemRepository
    public List<Item> createItems(List<Item> items) {
        List<Item> itemList = new ArrayList<>();

        for (Item i : items) {
            Item saveItem = Item.builder()
                    .id(i.hashCode())   // use item's hashcode as id to prevent duplicates
                    .unit(i.getUnit())
                    .price(i.getPrice())
                    .amount(i.getAmount())
                    .name(i.getName())
                    .brand(i.getBrand())
                    .store(i.getStore())
                    .category(i.getCategory())
                    .pricePerBaseAmount(calculatePricePerBaseAmount(i))
                    .build();
            itemList.add(saveItem);
        }
        return itemRepo.saveAll(itemList);
    }

    // helper method used in createItems()
    private double calculatePricePerBaseAmount(Item item) {
        // amount that converted to the base unit
        double result = -1;
        double baseAmount = unitToBaseMap.getBaseAmount(item.getUnit()) * item.getAmount();
        if (baseAmount != 0) {
            result = item.getPrice() / baseAmount;
        }
        result = Math.round(result * DECIMAL_PLACE);
        result = result / DECIMAL_PLACE;

        return result;
    }

    // creates a comparison obj using itemIds
    public Comparison getBestValue(String[] itemIdList) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < itemIdList.length; i++) {
            itemList.add(itemRepo.findItemById(Long.parseLong(itemIdList[i])));
        }
        return new Comparison(itemList);
    }

    // return search records from SearchRepository
    public List<Item> getSearchedItems(String text) {
        return searchRepo.findByText(text);
    }

    // remove an item from the ItemRepository by itemId
    public void deleteItem(String itemId) {
        itemRepo.deleteById(Long.parseLong(itemId));
    }

    // remove all items from the ItemRepository
    public void deleteAllItems() {
        itemRepo.deleteAll();
    }
}
