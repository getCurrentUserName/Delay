package com.delay.domain.entities.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.delay.domain.entities.product.Product;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.perishable.domain.entities.category;
 */
@Entity
@Table(name = "subcategory" + "_table")
@DynamicUpdate
@DynamicInsert
public class Subcategory {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CATEGORY = "category";

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = ID)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = NAME)
    private String name;

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = CATEGORY)
    private Category category;

    @JsonBackReference
    @OneToMany(mappedBy = Product.SUBCATEGORY, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
