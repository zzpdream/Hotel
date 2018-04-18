package com.zzpdream.hotel.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="t_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 100)
    private String name;
    @Column(length = 20)
    private String tel;
    @Column(length = 50)
    private String password;

    private String rolename;

    @ManyToOne(cascade = CascadeType.ALL,optional=true)  //(fetch=FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private Role role;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRolename() {
        return role.getName();
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
