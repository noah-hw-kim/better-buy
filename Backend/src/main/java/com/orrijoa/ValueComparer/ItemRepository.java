package com.orrijoa.ValueComparer;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    Item findItemByUnitAndPriceAndAmount(String unit, double price, double amount);
}
