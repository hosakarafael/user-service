package com.rafaelhosaka.rhv.user.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String imageUrl;
    private Date createdAt;
}
