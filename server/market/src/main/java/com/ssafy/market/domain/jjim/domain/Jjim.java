package com.ssafy.market.domain.jjim.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Jjim extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jjim_id")
    private Long jjimId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Jjim(Long jjimId, User user, Post post){
        this.jjimId = jjimId;
        this.user = user;
        this.post = post;
    }
    public void update(User user,Post post){
        this.user = user;
        this.post = post;
    }

}
