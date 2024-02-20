package com.orrijoa.BetterBuy;

import com.orrijoa.BetterBuy.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTests {
    @Autowired
    private ItemRepository repository;

    @Test
    public void findItemByIdShouldReturnItem() {
        repository.findById()
    }
}
