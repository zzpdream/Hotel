package com.zzpdream.hotel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_agenda")
public class Agenda {
    @Id
    @GeneratedValue
    @Column(name = "agenda_id")
    private Integer id;

    @Column(length = 100)
    private String content;
    @Column(length = 100)
    private String beginTime;
    @Column(length = 100)
    private String place;
    @Column(length = 100)
    private String remark;
    @Column(name = "meet_id")
    private Integer meetId;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    private List<AttachFiles> files=new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<AttachFiles> getFiles() {
        return files;
    }

    public void setFiles(List<AttachFiles> files) {
        this.files = files;
    }

    public Integer getMeetId() {
        return meetId;
    }

    public void setMeetId(Integer meetId) {
        this.meetId = meetId;
    }
}
