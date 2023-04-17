package com.orrijoa.ValueComparer.repository;

import com.orrijoa.ValueComparer.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<Item, Long> {

    Optional<List<Item>> findAllByOrderByCategoryAscPricePerBaseAmountAsc();

    Optional<Item> findItemByUnitAndPriceAndAmount(String unit, double price, double amount);

    Optional<List<Item>> findAllByCategoryAndSubCategoryOrderByPricePerBaseAmountAsc(String category, String subCategory);

    Item findItemById(long itemId);

    Item findTop1ByOrderByIdDesc();
}
