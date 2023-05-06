package com.orrijoa.ValueComparer.repository;

import com.orrijoa.ValueComparer.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, Long> {
    Item findItemById(long itemId);
}
