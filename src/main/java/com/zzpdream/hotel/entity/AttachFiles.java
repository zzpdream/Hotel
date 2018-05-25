package com.zzpdream.hotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Table(name = "attach_files")
@Entity
public class AttachFiles{

    private static final long serialVersionUID = 6512355591293984658L;
    private String title;
    private String pic;
    private String url;
    private String md5;
    private String remark;
    private Date createTime;

    @ManyToOne(cascade = CascadeType.ALL,optional=true)  //(fetch=FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    @JsonIgnore
    private Agenda agenda;

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Integer id;

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }


    @Column(name = "md5", length = 32)
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
