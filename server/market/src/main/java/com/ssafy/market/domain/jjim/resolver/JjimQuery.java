package com.ssafy.market.domain.jjim.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.jjim.domain.Jjim;
import com.ssafy.market.domain.jjim.dto.JjimOutput;
import com.ssafy.market.domain.jjim.repository.JjimRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostMetaOutput;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JjimQuery implements GraphQLQueryResolver {

    private final JjimRepository jjimRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    @Transactional
    public List<JjimOutput> findJjimByUserId(Long userId){
        User user = userRepository.findByUserId(userId);
        List<Jjim> jjimList = jjimRepository.findByUser(user);
        List<JjimOutput> outputs = new ArrayList<>();
        for (int i = 0; i< jjimList.size();i++){
            Jjim jjim = jjimList.get(i);
            Post post = jjim.getPost();
            Product product = productRepository.findByPost(post);
            File file = fileRepository.findTopByProduct(product);
            JjimOutput jjimOutput = new JjimOutput(jjim.getJjimId(),post.getPostId(),post.getTitle(),product.getCategory(),
                    file.getImgPath(),product.getPrice(),jjim.getCreatedDate().toString(),jjim.getModifiedDate().toString());
            outputs.add(jjimOutput);
        }
        return outputs;
    }
}
