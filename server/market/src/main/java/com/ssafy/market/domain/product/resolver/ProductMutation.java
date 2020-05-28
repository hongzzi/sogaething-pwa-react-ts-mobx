package com.ssafy.market.domain.product.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.dto.CreateProductInput;
import com.ssafy.market.domain.product.dto.ProductOutput;
import com.ssafy.market.domain.product.dto.UpdateProductInput;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.global.exception.DomainNotFoundException;
import com.ssafy.market.global.exception.DuplicateProductException;
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
        if(post == null) {
            throw new DomainNotFoundException("postId", input.getPostId());
        }
        if(post.getPostId() == input.getPostId()){
            throw new DuplicateProductException("postId" , input.getPostId());
        }
        Product product = productRepository.save(new Product(null,post, input.getName(),input.getPrice(),input.getCategory()));
        ProductOutput productOutput = new ProductOutput(product.getProductId(),post.getPostId(),product.getName(),product.getPrice(),product.getCategory());
        return productOutput;
    }
    @Transactional
    public int deleteProduct(Long id){
        Product product = productRepository.findById(id).get();
        if(product==null){
            throw new DomainNotFoundException("productId " , id);
        }
        return productRepository.deleteByProductId(id);
    }

    @Transactional
    public ProductOutput updateProduct(UpdateProductInput input){
        Post post= postRepository.findByPostId(input.getPostId());
        Product product = productRepository.findById(input.getProductId()).get();
        if(product==null){
            throw new DomainNotFoundException("productId", input.getProductId());
        }else if(post == null){
            throw new DomainNotFoundException("postId" , input.getPostId());
        }
        product.update(post,input.getName(),input.getPrice(),input.getCategory());
        ProductOutput po = new ProductOutput(product.getProductId(),product.getPost().getPostId(), product.getName(), product.getPrice(), product.getCategory());
        return po;
    }

}
