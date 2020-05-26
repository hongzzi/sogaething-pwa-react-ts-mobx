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
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class ProductMutation implements GraphQLMutationResolver {
    private final ProductRepository productRepository;
    private final PostRepository postRepository;

    @Transactional
    public ProductOutput createProduct(CreateProductInput input){
        Post post = postRepository.findByPostId(input.getPostId()).get();
        Product product = productRepository.save(new Product(null,post, input.getName(),input.getPrice(),input.getCategory(),input.getState()));
        ProductOutput productOutput = new ProductOutput(product.getProductId(),post.getPostId(),product.getName(),product.getPrice(),product.getCategory(),product.getState());
        return productOutput;
    }
    @Transactional
    public int deleteProduct(Long id){
//        Product product = productRepository.findById(input.getProductId()).get();
        return productRepository.deleteByProductId(id);
//       return product;
    }
    @Transactional
    public ProductOutput updateProduct(UpdateProductInput input){
        Post post = postRepository.findByPostId(input.getPostId()).get();
        Product product = productRepository.findById(input.getProductId()).get();
        product.update(post,input.getName(),input.getPrice(),input.getCategory(), input.getState());
//        productRepository.save(product);
        ProductOutput po = new ProductOutput(product.getProductId(),product.getPost().getPostId(), product.getName(), product.getPrice(), product.getCategory(),product.getState());
        return po;
    }

}
