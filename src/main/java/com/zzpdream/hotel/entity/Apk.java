package com.zzpdream.hotel.entity;

import javax.persistence.*;

@Entity
@Table(name="t_apk")
public class Apk {
    @Id
    @GeneratedValue
    @Column(name = "agenda_id")
    private Integer id;

    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private int versionCode;
    @Column(length = 100)
    private String url;

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

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
