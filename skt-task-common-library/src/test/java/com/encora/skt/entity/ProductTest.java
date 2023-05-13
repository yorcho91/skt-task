package com.encora.skt.entity;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private final Product product = new Product();

    @Test
    public void allArgsConstructor () {
        assertThat(new Product(0, "sku", "name", "description"))
            .extracting("id", "sku", "name", "description")
            .containsExactly(0L, "sku", "name", "description");
    }

    @Test
    public void id () {
        assertThat(product.setId(1).getId())
            .isEqualTo(1L);
    }

    @Test
    public void sku () {
        assertThat(product.setSku("sku").getSku())
            .isEqualTo("sku");
    }

    @Test
    public void name () {
        assertThat(product.setName("name").getName())
            .isEqualTo("name");
    }

    @Test
    public void description () {
        assertThat(product.setDescription("description").getDescription())
            .isEqualTo("description");
    }
}
