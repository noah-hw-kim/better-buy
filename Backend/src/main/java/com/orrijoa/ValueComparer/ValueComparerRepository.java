package com.orrijoa.ValueComparer;

import models.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueComparerRepository extends MongoRepository<Item, ObjectId> {
}
