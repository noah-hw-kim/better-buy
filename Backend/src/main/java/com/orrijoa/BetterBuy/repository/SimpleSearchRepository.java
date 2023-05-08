package com.orrijoa.BetterBuy.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.orrijoa.BetterBuy.models.Item;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* This repository find items in mongoDB with given condition
* */
@Component
public class SimpleSearchRepository implements SearchRepository {
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Override
    public List<Item> findByText(String text) {
        final List<Item> items = new ArrayList<>();

        MongoDatabase database = client.getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection("items");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search",
                new Document("text",
                new Document("query", text)
                .append("path", Arrays.asList("name", "brand", "store", "category")))),
                new Document("$sort",
                new Document("pricePerBaseAmount", 1L))));

        result.forEach(doc -> items.add(converter.read(Item.class, doc)));

        return items;
    }
}
