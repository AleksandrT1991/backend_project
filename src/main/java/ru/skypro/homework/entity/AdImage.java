package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * The type Ad image.
 */
@Entity
@Data
@Table(name = "ad_images")
public class AdImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "ad")
    private Ad adPk;

    @Column(name = "file_path")
    @Type(type = "org.hibernate.type.TextType")
    private String filePath;

    @Lob
    @Column(name = "byte")
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] bytea;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "media_type")
    @Type(type = "org.hibernate.type.TextType")
    private String mediaType;
}
