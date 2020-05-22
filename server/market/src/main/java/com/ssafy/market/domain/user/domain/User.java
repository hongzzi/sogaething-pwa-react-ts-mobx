package com.ssafy.market.domain.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String imageUrl;

    @NotNull
    @Column(nullable = false)
    private String provider;

    private Long providerId;

    private String phone;

    private String address;

    private int trust;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, @Email String email, String imageUrl, @NotNull String provider, Long providerId, String phone, String address, int trust, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
        this.phone = phone;
        this.address = address;
        this.trust = trust;
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", provider='" + provider + '\'' +
                ", providerId='" + providerId + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", trust=" + trust +
                ", role=" + role +
                '}';
    }
}

