package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "ads")
public class Ad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pk", nullable = false)
    private Long pk;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    @Type(type = "org.hibernate.type.TextType")
    private String title;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "phone")
    @Type(type = "org.hibernate.type.TextType")
    private String phone;

    @Column(name = "author_first_name")
    @Type(type = "org.hibernate.type.TextType")
    private String authorFirstName;

    @Column(name = "author_last_name")
    @Type(type = "org.hibernate.type.TextType")
    private String authorLastName;

    @Column(name = "email")
    @Type(type = "org.hibernate.type.TextType")
    private String email;

    @Column(name = "image")
    @Type(type = "org.hibernate.type.TextType")
    private List<String> image;

    @Column(name = "author")
    private Long author;

}
