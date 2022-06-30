package com.project.library.model;

import javax.persistence.*;
import java.sql.Date;
//import java.util.Date;
import java.util.Calendar;

@Entity
@Table(name = "borrows")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    private Date effectiveDate;
    private int duration;
    @ManyToOne
    @JoinColumn( name = "member_id" )
    private Member member;
    @ManyToOne
    @JoinColumn( name = "copy_id" )
    private Copy copy;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    //Constructors

    public Borrow() {
    }

    public Borrow(Long id, Date startDate, Date endDate, Date effectiveDate, int duration) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.effectiveDate = effectiveDate;
        this.duration = duration;
    }

    public Borrow(Long id, Date startDate, Date endDate, Date effectiveDate, int duration, Member member, Copy copy) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.effectiveDate = effectiveDate;
        this.duration = duration;
        this.member = member;
        this.copy = copy;
    }

    //Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //StartDate
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //EndDate
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //EffectiveDate
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    //Duration
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //Member
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    //Copy
    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    //Created&Updated
    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
