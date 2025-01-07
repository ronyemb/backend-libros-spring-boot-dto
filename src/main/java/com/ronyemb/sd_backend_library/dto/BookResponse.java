package com.ronyemb.sd_backend_library.dto;

import com.ronyemb.sd_backend_library.model.Image;

public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private Double price;
    private Image image;

    // Constructor
    public BookResponse() {
    }

    public BookResponse(Long id, String title, String author, Integer pages, Double price, Image image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.image = image;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
