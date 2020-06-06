package com.ssafy.market.domain.file.repository;

import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.dto.ProductOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

//    File findByProduct(Product product);
    File findByFileId(Long id);
    int deleteByFileId(Long id);
    List<File> findByProduct(Product product);

    File findTopByProduct(Product product);
    @Query(value = "SELECT DISTINCT img_path FROM file WHERE product_id = :product", nativeQuery = true)
    List<String> findFilePathByProduct(@Param("product") Long productId);
    File findTop1ByProduct(Product product);

}
