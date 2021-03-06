package com.example.devedbaseproject.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity // сущность связана с БД
@Table(name = "product") // имя, связанной таблицы
public class Product {


    public Product(Long id, String productName, String description,
                   Long productQuantity) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.productQuantity = productQuantity;
        this.manufacturer = new Manufacturer();
        this.productSubtype = new ProductSubtype();
        this.parameterValues = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @ManyToOne//(cascade = { CascadeType.ALL })
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer; // идет в класс Manufacturer
    // и связывается по primary key

    @Column(name = "product_quantity")
    private Long productQuantity;

    @ManyToOne //(cascade = { CascadeType.ALL })
    @JoinColumn(name = "product_subtype_id")
    private ProductSubtype productSubtype;

    @OneToMany
    @JoinColumn(name = "parameter_values")
    private List<ProductParameterValue> parameterValues;

    @OneToMany
    @JoinColumn(name = "tags")
    private List<Tag> tags;

}
