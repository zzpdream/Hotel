package com.zzpdream.hotel.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_order")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 100)
    private String orderName;
    @Column(length = 20)
    private Integer orderPrice;
    @Column(length = 50)
    private String orderTel;

    @Column(length = 20)
    private Integer orderRoomId;

//    @Column(length = 20)
//    private String orderRoomName;

    @Column(name = "beginTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginTime;

    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public Order(String orderName, Integer orderPrice, String orderTel, Integer orderRoomId, String orderRoomName, Date beginTime, Date endTime) {
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderTel = orderTel;
        this.orderRoomId = orderRoomId;
//        this.orderRoomName = orderRoomName;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderRoomId() {
        return orderRoomId;
    }

    public void setOrderRoomId(Integer orderRoomId) {
        this.orderRoomId = orderRoomId;
    }

//    public String getOrderRoomName() {
//        return orderRoomName;
//    }
//
//    public void setOrderRoomName(String orderRoomName) {
//        this.orderRoomName = orderRoomName;
//    }
}
