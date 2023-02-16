package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Column(name = "email")
    @Type(type = "org.hibernate.type.TextType")
    private String email;

    @Column(name = "first_name")
    @Type(type = "org.hibernate.type.TextType")
    private String firstName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "last_name")
    @Type(type = "org.hibernate.type.TextType")
    private String lastName;

    @Column(name = "phone")
    @Type(type = "org.hibernate.type.TextType")
    private String phone;

    @Column(name = "reg_date")
    @Type(type = "org.hibernate.type.TextType")
    private String regDate;

    @Column(name = "city")
    @Type(type = "org.hibernate.type.TextType")
    private String city;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image")
    private UserImage image;

    @Column(name = "password")
    @Type(type = "org.hibernate.type.TextType")
    private String password;

}
