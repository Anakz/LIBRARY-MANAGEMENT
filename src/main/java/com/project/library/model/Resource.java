package com.project.library.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "bar_code")
    private String barCode;
    private String author;
    private String edition;
    @Column(name = "number_page")
    private int numberPage;
    @Column(name = "release_date")
    private Date releaseDate;
    private String theme;
    private String language;
    @Column(name = "number_copy")
    private int numberCopy;
    private String availability;
    private String type;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
    @ManyToOne
    @JoinColumn( name = "position_id" )
    private Position position;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    //Constructors
    public Resource() {
    }

    public Resource(Long id, String title, String barCode, String author, String edition, int numberPage, Date releaseDate, String theme, String language, int numberCopy, String availibility, String type, String image) {
        this.id = id;
        this.title = title;
        this.barCode = barCode;
        this.author = author;
        this.edition = edition;
        this.numberPage = numberPage;
        this.releaseDate = releaseDate;
        this.theme = theme;
        this.language = language;
        this.numberCopy = numberCopy;
        this.availability = availibility;
        this.type = type;
        this.image = image;
    }

    public Resource(Long id, String title, String barCode, String author, String edition, int numberPage, Date releaseDate, String theme, String language, int numberCopy, String availability, String type, String image, Position position) {
        this.id = id;
        this.title = title;
        this.barCode = barCode;
        this.author = author;
        this.edition = edition;
        this.numberPage = numberPage;
        this.releaseDate = releaseDate;
        this.theme = theme;
        this.language = language;
        this.numberCopy = numberCopy;
        this.availability = availability;
        this.type = type;
        this.image = image;
        this.position = position;
    }

    //Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //BarCode
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    //Author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //Edition
    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    //NumberPage
    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    //ReleaseDate
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    //Theme
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    //Language
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    //NumberCopy
    public int getNumberCopy() {
        return numberCopy;
    }

    public void setNumberCopy(int numberCopy) {
        this.numberCopy = numberCopy;
    }

    //Availability
    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    //Type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //Position
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    //Created&Updated
    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString(){
        return "Title: "+this.getTitle()+", Author: "+ getAuthor();
    }
}
