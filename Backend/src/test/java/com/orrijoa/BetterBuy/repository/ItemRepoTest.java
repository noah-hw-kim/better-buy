package com.orrijoa.BetterBuy.repository;

import com.orrijoa.BetterBuy.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.io.IOException;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemRepoTest {
    @Autowired
    private ItemRepository itemRepository;

    @BeforeAll
    public void setup() {
        itemRepository.deleteAll();
        itemRepository.save(Item.builder().name("tofu1").build());
        itemRepository.save(Item.builder().name("tofu2").build());
        itemRepository.save(Item.builder().name("tofu1").build());
    }

    @Test
    public void testGetById() throws IOException {
        Assertions.assertEquals("tofu1", itemRepository.findItemsByName("tofu1").get(0).getName());
    }
}
