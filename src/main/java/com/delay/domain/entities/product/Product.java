package com.delay.domain.entities.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.delay.domain.entities.category.Subcategory;
import com.delay.domain.entities.company.Company;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by LucidMinds on 18.05.16.
 * package com.delay.domain.entities.product;
 */
@Entity
@Table(name = "product" + "_table")
@DynamicUpdate
@DynamicInsert
public class Product {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String COUNT = "count";
    public static final String PRICE = "price";
    public static final String ADDITION_DATE = "additionDate";
    public static final String REMOVAL_DATE = "removalDate";
    public static final String EXPIRE_DATE = "expireDate";
    public static final String COUNTRY = "country";
    public static final String STATUS = "status";
    public static final String SUBCATEGORY = "subcategory";
    public static final String COMPANY = "company";

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = ID)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = NAME)
    private String name;

    @Column(name = COUNT)
    private long count;

    @Column(name = PRICE)
    private double price;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = ADDITION_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date additionDate;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = REMOVAL_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date removalDate;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = EXPIRE_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @Column(name = COUNTRY)
    private String country;

    @JsonIgnore
    @Column(name = STATUS)
    private String status;

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = SUBCATEGORY)
    private Subcategory subcategory;

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = COMPANY)
    private Company company;

    @JsonBackReference
    @OneToMany(mappedBy = Sales.PRODUCT, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sales> sales = new ArrayList<>();

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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    public Date getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(Date removalDate) {
        this.removalDate = removalDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }
}
