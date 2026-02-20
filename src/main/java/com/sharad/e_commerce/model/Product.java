package com.sharad.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private BigDecimal price;
    private int stockQuantity;
    private String brand;
    private boolean productAvailable;
    private String category;
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy") - handled by fe as (product.releaseDate).toLocaleDateString()
    private Date releaseDate;
    private String description;

    //image desc
    private String imageType;
    private String imageName;

    //image
    @Lob
    private byte[] imageData;

}
