package com.encora.skt.repository;

import com.encora.skt.entity.Product;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository
         extends JpaRepository<Product, Long>
{
    @Query(value = "call list_products", nativeQuery = true)
    List<Product> listProducts();

    @Modifying
    @Transactional
    @Query(value = "call insert_product(?, ?, ?)", nativeQuery = true)
    void insertProduct(String sku, String name, String description);

    @Transactional
    Long deleteBySku(String sku);

}
