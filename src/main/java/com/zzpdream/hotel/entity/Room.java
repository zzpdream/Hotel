package com.zzpdream.hotel.entity;

import javax.persistence.*;

@Entity
@Table(name="t_room")
public class Room {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 100)
    private String name;
    @Column(length = 20)
    private Integer price;
    @Column(length = 500)
    private String info;

    @Column(length = 100)
    private String img;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
