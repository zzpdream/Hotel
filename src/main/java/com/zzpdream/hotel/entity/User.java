package com.zzpdream.hotel.entity;


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
}
