package com.ssafy.market.domain.hashtag.domain;

import com.ssafy.market.domain.matching.domain.Matching;
import com.ssafy.market.domain.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="matching_hashtag")
public class MatchingHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "matching_hashtag_id")
    private Long matchingHashtagId;

    @ManyToOne
    @JoinColumn(name = "matching_id")
    private Matching matching;

    private String hashtag;

    public MatchingHashtag(Matching matching, String hashtag){
        this.matching = matching;
        this.hashtag = hashtag;
    }

    @Override
    public String toString() {
        return "MatchingHashtag{" +
                "matchingHashtagId=" + matchingHashtagId +
                ", matching=" + matching +
                ", hashtag='" + hashtag + '\'' +
                '}';
    }
}