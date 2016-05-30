package com.delay.domain.entities.company;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.delay.domain.entities.category.Category;
import com.delay.domain.entities.user.User;
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
 * package com.delay.domain.entities.company;
 */
@Entity
@Table(name = "company" + "_table")
@DynamicUpdate
@DynamicInsert
public class Company {

    public static final String ID = "id";
    public static final String NAME = "name";

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = ID)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = NAME)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = Category.COMPANY, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = User.COMPANY, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
