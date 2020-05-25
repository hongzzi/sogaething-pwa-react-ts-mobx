package com.ssafy.market.domain.file.repository;

import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    File findByProduct(Product product);
    int deleteByFileId(Long id);

}
