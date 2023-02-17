package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * The type User image.
 */
@Entity
@Data
@Table(name = "user_images")
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

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
