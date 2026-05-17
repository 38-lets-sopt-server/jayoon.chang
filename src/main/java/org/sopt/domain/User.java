package org.sopt.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    private String password;

    protected User() {}

    public User(String nickname, String email, String password){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
