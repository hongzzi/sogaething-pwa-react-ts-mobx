package com.ssafy.market.domain.product.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.dto.ProductOutput;
import com.ssafy.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductQuery implements GraphQLQueryResolver {
    private final ProductRepository productRepository;
    private final PostRepository postRepository;


    @Transactional
    public List<ProductOutput> findAllProduct()
    {
        List<ProductOutput> outputs = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        System.out.println(productList.size());
        for(int i = 0; i< productList.size(); i++){
            outputs.add(new ProductOutput(productList.get(i).getProductId(),
                    productList.get(i).getPost().getPostId(),
                    productList.get(i).getName(),
                    productList.get(i).getPrice(),
                    productList.get(i).getCategory(),
                    productList.get(i).getState()));
        }
        return outputs;
    }
    public Iterable<Product> findAllProducts(){
        return productRepository.findAll();
    }

    @Transactional
    public ProductOutput findByProductId(Long productId) {
        Product product = productRepository.findByProductId(productId);

        ProductOutput output = new ProductOutput(productId, product.getPost().getPostId(), product.getName(),product.getPrice(), product.getCategory(),product.getState());
//        System.out.println(productRepository.findByProductId(productId));
      return output;
    }


}