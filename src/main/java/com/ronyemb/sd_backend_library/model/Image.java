package com.ronyemb.sd_backend_library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString // Facilita la depuración
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
    public Image(String name, String imageUrl, String imageId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }
}

