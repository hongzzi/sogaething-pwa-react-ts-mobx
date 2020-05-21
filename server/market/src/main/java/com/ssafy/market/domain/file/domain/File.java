package com.ssafy.market.domain.file.domain;

import com.ssafy.market.domain.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long file_id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    private Long post_id;

    private String img_path;
}
