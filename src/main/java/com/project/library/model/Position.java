package com.project.library.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theme;
    private String floor;
    private String row;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Position() {
    }

    public Position(Long id, String theme, String floor, String row) {
        this.id = id;
        this.theme = theme;
        this.floor = floor;
        this.row = row;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString(){
        return "Floor: "+this.getFloor()+", Theme: "+this.getTheme()+", Row: "+ this.getRow();
    }
}
