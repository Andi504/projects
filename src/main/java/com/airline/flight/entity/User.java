package com.airline.flight.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false,updatable = false,unique = true)
    private Long uid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_disable", nullable = false)
    private Boolean isDisable;


    @ManyToMany(fetch = FetchType.EAGER,  cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "uid",referencedColumnName = "uid"),
            inverseJoinColumns = @JoinColumn(name = "rid",referencedColumnName = "rid")
    )
    @JsonIgnore
    @SortNatural
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Trip> trips;

    public User(Long uid, String name, String username, String password, Boolean isDisable, List<Role> roles, List<Trip> trips) {
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isDisable = isDisable;
        this.roles = roles;
        this.trips = trips;
    }

    public User() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDisable() {
        return isDisable;
    }

    public void setDisable(Boolean disable) {
        isDisable = disable;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isDisable=" + isDisable +
                ", roles=" + roles +
                ", trips=" + trips +
                '}';
    }
}
