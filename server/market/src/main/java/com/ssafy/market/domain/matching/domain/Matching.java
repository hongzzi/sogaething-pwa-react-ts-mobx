package com.ssafy.market.domain.matching.domain;
import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name="matching")
public class Matching extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_id")
    private Long matchingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
    private Long userId;

    private String category;

    private int minPrice;

    private int maxPrice;

    private String transaction;

    public Matching(User user, String category, int minPrice, int maxPrice, String transaction) {
        this.user = user;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.transaction = transaction;
    }
}
