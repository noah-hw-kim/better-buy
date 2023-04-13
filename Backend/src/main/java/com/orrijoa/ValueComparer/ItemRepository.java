package com.orrijoa.ValueComparer;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    List<Item> findAllByOrderByCategoryAscPricePerBaseAmountAsc();

    Optional<Item> findItemByUnitAndPriceAndAmount(String unit, double price, double amount);
}
