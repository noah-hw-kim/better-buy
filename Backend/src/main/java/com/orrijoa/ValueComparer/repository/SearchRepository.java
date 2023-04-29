package com.orrijoa.ValueComparer.repository;

import com.orrijoa.ValueComparer.models.Item;

import java.util.List;

public interface SearchRepository {
    List<Item> findByText(String text);
}
