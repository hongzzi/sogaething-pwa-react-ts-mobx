package com.ssafy.market.domain.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Document(collection = "collectionCounter")
public class CollectionCounter implements Serializable {
    private static final long serialVersionUID = 3L;

    private String name;
    private Long seq;
}
