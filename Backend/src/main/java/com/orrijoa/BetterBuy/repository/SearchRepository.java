package com.orrijoa.BetterBuy.repository;

import com.orrijoa.BetterBuy.models.Item;
import java.util.List;

public interface SearchRepository {
    List<Item> findByText(String text);
}
