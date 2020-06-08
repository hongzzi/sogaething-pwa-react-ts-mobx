package com.ssafy.market.domain.product.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.dto.CreateProductInput;
import com.ssafy.market.domain.product.dto.ProductOutput;
import com.ssafy.market.domain.product.dto.UpdateProductInput;
import com.ssafy.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductMutation implements GraphQLMutationResolver {
    private final ProductRepository productRepository;
    private final PostRepository postRepository;

    @Transactional
    public ProductOutput createProduct(CreateProductInput input) {
        Post post =  postRepository.findByPostId(input.getPostId());
        Product product = productRepository.save(new Product(null,post, input.getPrice(),input.getCategory(),(long)0));
        ProductOutput productOutput = new ProductOutput(product.getProductId(),post.getPostId(),product.getName(),product.getPrice(),product.getCategory());
        return productOutput;
    }
    @Transactional
    public int deleteProduct(Long id){
        return productRepository.deleteByProductId(id);
    }

    @Transactional
    public ProductOutput updateProduct(UpdateProductInput input){
        Post post= postRepository.findByPostId(input.getPostId());
        Product product = productRepository.findById(input.getProductId()).get();
        product.update(post,input.getPrice(),input.getCategory());
        ProductOutput po = new ProductOutput(product.getProductId(),product.getPost().getPostId(), product.getName(), product.getPrice(), product.getCategory());
        return po;
    }

}
