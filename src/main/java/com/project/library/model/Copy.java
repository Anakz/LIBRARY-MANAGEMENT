package com.project.library.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "copies")
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn( name = "resource_id" )
    private Resource resource;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    //Constructors\\
    public Copy() {
    }

    public Copy(Long id) {
        this.id = id;
    }

    public Copy(Long id, Resource resource) {
        this.id = id;
        this.resource = resource;
    }
    //Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //Resource
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    //Created&Updated
    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
