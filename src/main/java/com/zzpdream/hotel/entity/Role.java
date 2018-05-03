package com.zzpdream.hotel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="t_role")
public class Role{
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer id;

    @Column(length = 100)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Set<User> users=new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
