package com.ronyemb.sd_backend_library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;


@ToString(exclude = "imageId") // Facilita la depuración
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Image URL cannot be blank")
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "Invalid URL format")
    private String imageUrl;

    @NotBlank(message = "Image ID cannot be blank")
    private String imageId;

    // Constructor sin ID para crear objetos sin ID
    public Image(){}

    public Image(String name, String imageUrl, String imageId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name cannot be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be blank") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Image URL cannot be blank") @Pattern(regexp = "^(https?|ftp)://[^ /$.?#].[^ ]*$", message = "Invalid URL format") String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NotBlank(message = "Image URL cannot be blank") @Pattern(regexp = "^(https?|ftp)://[^ /$.?#].[^ ]*$", message = "Invalid URL format") String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public @NotBlank(message = "Image ID cannot be blank") String getImageId() {
        return imageId;
    }

    public void setImageId(@NotBlank(message = "Image ID cannot be blank") String imageId) {
        this.imageId = imageId;
    }
}

