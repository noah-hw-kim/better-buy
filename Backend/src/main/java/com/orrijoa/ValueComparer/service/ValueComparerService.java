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

    public List<Item> createItems(List<Item> items) {
        // use item's hashcode for the id to prevent the duplicates
        List<Item> itemList = new ArrayList<>();

        for (Item i : items) {
            Item saveItem = Item.builder()
                    .id(i.hashCode())
                    .unit(i.getUnit())
                    .price(i.getPrice())
                    .amount(i.getAmount())
                    .name(i.getName())
                    .brand(i.getBrand())
                    .store(i.getStore())
                    .category(i.getCategory())
                    .subCategory(i.getSubCategory())
                    .pricePerBaseAmount(calculatePricePerBaseAmount(i))
                    .build();

            itemList.add(saveItem);
        }
        return itemRepo.saveAll(itemList);
    }

    private double calculatePricePerBaseAmount(Item item) {
        // amount that converted to the base unit
        double result = -1;
        double baseAmount = UnitConverter.getStandardAmount(item.getUnit()) * item.getAmount();
        if (baseAmount != 0) {
            result = item.getPrice() / baseAmount;
        }

        return result;
    }

    public UnitList getUnitList() {
        return unitList;
    }

    public Categories getCategories() {
        return categories;
    }

    public List<Item> getAllItems() {
        return itemRepo.findAll(Sort.by(Sort.Direction.ASC, "category").and(Sort.by(Sort.Direction.ASC, "pricePerBaseAmount")));
    }

    public List<Item> getItemsWithName(String name) {
        return itemRepo.findAllByNameOrderByPricePerBaseAmountAsc(name);
    }


    public List<Item> getItems(String text) {
        return searchRepo.findByText(text);
    }


    public List<Item> getItemsByCategoryAndSubCategory(String category, String subCategory) {
        return itemRepo.findAllByCategoryAndSubCategoryOrderByPricePerBaseAmountAsc(category, subCategory);
    }

    // create item 1 and item 2 objects with inputs and compare the value and return the cheaper item
    public Comparison getCheaper(String[] itemIdList) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < itemIdList.length; i++) {
            itemList.add(itemRepo.findItemById(Long.parseLong(itemIdList[i])));
        }
        return new Comparison(itemList);
    }

    // remove an item from the db with itemId
    public void deleteItem(String itemId) {
        itemRepo.deleteById(Long.parseLong(itemId));
    }

    // remove all items in the db
    public void deleteAllItems() {
        itemRepo.deleteAll();
    }

}
