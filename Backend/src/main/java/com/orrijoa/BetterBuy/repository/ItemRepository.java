package com.orrijoa.BetterBuy.repository;

import com.orrijoa.BetterBuy.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
//    Item findItemById(long itemId);
    Item findItemById(String itemId);
    List<Item> findItemsByName(String name);
}
