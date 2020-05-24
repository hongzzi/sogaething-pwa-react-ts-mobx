package com.ssafy.market.domain.product.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.respository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.dto.CreateProductInput;
import com.ssafy.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class ProductMutation implements GraphQLMutationResolver {
    private final ProductRepository productRepository;
    private final PostRepository postRepository;

    @Transactional
    public Product createProduct(CreateProductInput input){
        Post post = postRepository.findByPostId(input.getPostId()).get();

        return productRepository.save(new Product(null,post, input.getName(),input.getPrice(),input.getCategory(),input.getState()));

    }

}
