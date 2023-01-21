package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "file")
    @Type(type = "org.hibernate.type.TextType")
    private String file;

    @Column(name = "pk")
    private Integer pk;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    @Type(type = "org.hibernate.type.TextType")
    private String title;
}
