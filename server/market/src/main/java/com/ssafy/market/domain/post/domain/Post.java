package com.ssafy.market.domain.post.domain;

import com.ssafy.market.domain.BaseTimeEntity;
import com.ssafy.market.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long post_id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id")
    private Long uploader_id;

    private boolean is_buy;

    private String title;

    private Date sale_date;

    private String contents;

    private Long view_count;

    private String deal;

    private String deal_state;

    public Post(Long post_id) {
        this.post_id = post_id;
    }

    public Post(Long uploader_id, String title, Date sale_date, String contents, String deal) {
        this.uploader_id = uploader_id;
        this.title = title;
        this.sale_date = sale_date;
        this.contents = contents;
        this.deal = deal;
    }

    public Post(Long post_id, Long uploader_id, boolean is_buy, String title, Date sale_date, String contents, Long view_count, String deal, String deal_state) {
        this.post_id = post_id;
        this.uploader_id = uploader_id;
        this.is_buy = is_buy;
        this.title = title;
        this.sale_date = sale_date;
        this.contents = contents;
        this.view_count = view_count;
        this.deal = deal;
        this.deal_state = deal_state;
    }
}

