package com.ssafy.market.domain.product.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductQuery implements GraphQLQueryResolver {
    private final ProductRepository productRepository;

    @Transactional
    public Iterable<Product> findAllProducts() {
        return productRepository.findAll();
    }
//    @Transactional
//    public List<Product> findAllProduct(){return productRepository.findAll();}
//    public Optional<Post> findPostByPostId(Long id) {
//        return postRepository.findByPost_id(id);
//    }

    @Transactional
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

//    public Iterable<Post> findAllPostsByUploaderId() {
//        return postRepository.findAll();
//    }
}