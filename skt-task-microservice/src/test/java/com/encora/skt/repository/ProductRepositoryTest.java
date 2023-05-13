package com.encora.skt.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void testStoredProcedures() {
        repository.insertProduct(
            "zzz",
            "name",
            "description");
        assertThat(repository.listProducts())
            .extracting("sku")
            .contains("zzz");
    }
}
