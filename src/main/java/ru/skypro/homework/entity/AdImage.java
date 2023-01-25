package ru.skypro.homework.entity;


import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
@Entity
@Data
@Table
public class AdImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ad-id")
    private Integer adId ;

    @Column(name = "file_path")
    @Type(type = "org.hibernate.type.TextType")
    private String filePath;

    @Column(name = "file_size")
    private Integer fileSize ;

    @Column(name = "media_type")
    @Type(type = "org.hibernate.type.TextType")
    private String mediaType;
}
