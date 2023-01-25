package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
public class AdImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ad_id")
    private Long adId;

    @Column(name = "file_path")
    @Type(type = "org.hibernate.type.TextType")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "media_type")
    @Type(type = "org.hibernate.type.TextType")
    private String mediaType;
}
