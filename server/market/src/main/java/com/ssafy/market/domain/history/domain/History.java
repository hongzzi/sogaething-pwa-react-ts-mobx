package com.ssafy.market.domain.history.domain;

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
@Table(name="history")
public class History extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "post_id", insertable = false, updatable = false, nullable = false)
    private Long postId;

    public History(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public History(Long historyId, User user, Post post) {
        this.historyId = historyId;
        this.user = user;
        this.post = post;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", user=" + user +
                ", userId=" + userId +
                ", post=" + post +
                ", postId=" + postId +
                '}';
    }
}
