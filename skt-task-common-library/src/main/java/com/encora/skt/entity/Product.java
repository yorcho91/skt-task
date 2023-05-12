package com.encora.skt.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 16, nullable = false, unique=true)
    private String sku;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 64)
    private String description;

}
