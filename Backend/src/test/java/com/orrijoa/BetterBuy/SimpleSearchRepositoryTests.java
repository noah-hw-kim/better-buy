package com.orrijoa.BetterBuy;

import com.orrijoa.BetterBuy.models.Item;
import com.orrijoa.BetterBuy.repository.SearchRepository;
import com.orrijoa.BetterBuy.repository.SimpleSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SimpleSearchRepositoryTests {
    @Mock
    private SearchRepository searchRepository;

    @InjectMocks
    private SimpleSearchRepository simpleSearchRepository;

    @Test
    public void findByTextTest() {
        Item item = new Item(5.0,"ounce");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        when(simpleSearchRepository.findByText(any(String.class))).thenReturn(itemList);

    }
}
