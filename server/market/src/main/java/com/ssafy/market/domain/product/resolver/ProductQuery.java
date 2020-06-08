package com.ssafy.market.domain.product.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
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

    @Transactional
    public List<ProductOutput> findAllProduct()
    {
        List<ProductOutput> outputs = new ArrayList<>();
        List<Product> productList = productRepository.findAll();

        for(int i = 0; i< productList.size(); i++){
            outputs.add(new ProductOutput(productList.get(i).getProductId(),
                    productList.get(i).getPost().getPostId(),
                    productList.get(i).getName(),
                    productList.get(i).getPrice(),
                    productList.get(i).getCategory()
                    ));
        }
        return outputs;
    }
    public Iterable<Product> findAllProducts(){
        return productRepository.findAll();
    }

    @Transactional
    public ProductOutput findByProductId(Long id) {
        Product product = productRepository.findByProductId(id);
        ProductOutput output = new ProductOutput(id, product.getPost().getPostId(), product.getName(),product.getPrice(), product.getCategory());
      return output;
    }


}