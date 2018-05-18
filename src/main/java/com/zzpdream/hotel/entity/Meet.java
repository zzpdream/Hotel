package com.zzpdream.hotel.entity;

import javax.persistence.*;

@Entity
@Table(name="t_meet")
public class Meet {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer id;
    @Column(length = 100)
    private String subject;
    @Column(length = 100)
    private String beginTime;
    @Column(length = 100)
    private String place;
    @Column(length = 100)
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
