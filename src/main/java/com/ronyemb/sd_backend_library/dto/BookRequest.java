package com.ronyemb.sd_backend_library.dto;

import com.ronyemb.sd_backend_library.model.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookRequest {

    @NotBlank(message = "Title is mandatory")
    @Column(length = 255)
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Column(length = 255)
    private String author;

    @NotNull(message = "Pages cannot be null")
    @Positive(message = "Pages must be a positive number")
    private Integer pages;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    public @NotBlank(message = "Title is mandatory") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is mandatory") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author is mandatory") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author is mandatory") String author) {
        this.author = author;
    }

    public @NotNull(message = "Pages cannot be null") @Positive(message = "Pages must be a positive number") Integer getPages() {
        return pages;
    }

    public void setPages(@NotNull(message = "Pages cannot be null") @Positive(message = "Pages must be a positive number") Integer pages) {
        this.pages = pages;
    }

    public @NotNull(message = "Price cannot be null") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price cannot be null") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero") Double price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
