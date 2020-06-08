package com.ssafy.market.domain.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userId")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, String name, @Email String email, String imageUrl, @NotNull String provider, Long providerId, String phone, String address, int trust, Role role) {
        this.userId = userId;
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
    public void update( String imageUrl, String phone, String address,int trust){
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.address = address;
        this.trust = trust;
    }
    public void update(String name, String imageUrl, String phone, String address){
        this.name = name;
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.address = address;
    }
    public void updateimg(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public void setTrust (int trust){
        this.trust = trust;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
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

