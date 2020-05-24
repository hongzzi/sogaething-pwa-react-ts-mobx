package com.ssafy.market.domain.file.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.dto.CreateFileInput;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class FileMutation implements GraphQLMutationResolver {

    private  final FileRepository fileRepository;
    private  final ProductRepository productRepository;

    @Transactional
    public File createFile(CreateFileInput input){
        Product product = productRepository.findById(input.getProductId()).get();
        return fileRepository.save(new File(null, product,input.getImgPath()));
    }
}
