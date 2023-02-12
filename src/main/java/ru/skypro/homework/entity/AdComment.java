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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    @Type(type = "org.hibernate.type.TextType")
    private String createdAt;

    @Column(name = "pk")
    private Long pk;

    @Column(name = "text")
    @Type(type = "org.hibernate.type.TextType")
    private String text;

}
