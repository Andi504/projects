package com.airline.flight.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid", nullable = false,updatable = false,unique = true)
    private Long rid;

    @Column(name = "role")
    private String name;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Collection<User> users = new ArrayList<>();

    public Role(Long id, String name) {
        this.rid = id;
        this.name = name;
    }

    public Role() {
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", name='" + name + '\'' +
                '}';
    }
}

