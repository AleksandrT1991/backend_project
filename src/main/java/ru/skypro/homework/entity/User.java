package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    @Type(type = "org.hibernate.type.TextType")
    private String userName;

    @Column(name = "first_name")
    @Type(type = "org.hibernate.type.TextType")
    private String firstName;

    @Column(name = "last_name")
    @Type(type = "org.hibernate.type.TextType")
    private String lastName;

    @Column(name = "email")
    @Type(type = "org.hibernate.type.TextType")
    private String email;

    @Column(name = "password")
    @Type(type = "org.hibernate.type.TextType")
    private String password;

    @Column(name = "phone")
    @Type(type = "org.hibernate.type.TextType")
    private String phone;

    @Column(name= "regdate")
    @Type(type = "org.hibernate.type.TextType")
    private String regDate;

    @Column(name= "city")
    @Type(type = "org.hibernate.type.TextType")
    private String city;

    @Column(name= "userimage")
    @Type(type = "org.hibernate.type.TextType")
    private String userImage;




//    @Column(name = "userstatus")
//    @Type(type = "org.hibernate.type.TextType")
//    private Integer userStatus;












}
