package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ad_comments")
public class AdComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "author")
    private Long author;

    @Column(name = "created_ad")
    @Type(type = "org.hibernate.type.TextType")
    private String createdAd;

    @JoinColumn(name = "pk")
    private Long pk;

    @Column(name = "text")
    @Type(type = "org.hibernate.type.TextType")
    private String text;
}
