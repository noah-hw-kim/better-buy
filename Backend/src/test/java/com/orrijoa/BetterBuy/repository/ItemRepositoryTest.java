package com.orrijoa.BetterBuy.repository;

import com.orrijoa.BetterBuy.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @BeforeAll
    public void setup() {
        itemRepository.deleteAll();
        itemRepository.save(Item.builder().id("1").name("tofu1").build());
        itemRepository.save(Item.builder().id("2").name("tofu2").build());
        itemRepository.save(Item.builder().id("3").name("tofu1").build());
    }

    @Test
    public void testFindItemById() throws IOException {
        assertEquals("tofu1", itemRepository.findItemById("1").getName());
        assertEquals("tofu2", itemRepository.findItemById("2").getName());
        assertEquals("tofu1", itemRepository.findItemById("3").getName());
    }

    @Test
    public void testFindItemByName() throws IOException {
        List<Item> actual = itemRepository.findItemsByName("tofu1");

        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(2);

        List<Item> expect = new ArrayList<>();
        expect.add(Item.builder().id("1").name("tofu1").build());
        assertThat(actual.get(0).equals(expect.get(0))).isTrue();

        expect.add(Item.builder().id("2").name("tofu1").build());
        assertThat(actual.get(1).equals(expect.get(1))).isFalse();


    }
}
