package com.ssafy.market.domain.file.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.dto.CreateFileInput;
import com.ssafy.market.domain.file.dto.FileOutput;
import com.ssafy.market.domain.file.dto.UpdateFileInput;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.global.exception.DomainNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class FileMutation implements GraphQLMutationResolver {

    private  final FileRepository fileRepository;
    private  final ProductRepository productRepository;

    @Transactional
    public FileOutput createFile(CreateFileInput input){
        Product product = productRepository.findByProductId(input.getProductId());
        if(product==null){
            throw new DomainNotFoundException("productId",input.getProductId());
        }
        File file = fileRepository.save(new File(null, product,input.getImgPath()));
        FileOutput output = new FileOutput(file.getFileId(), product.getProductId(), input.getImgPath());
        return output;
    }
    @Transactional
    public FileOutput updateFile(UpdateFileInput input){
        File file = fileRepository.findByFileId(input.getFileId());
        if(file==null){
            throw new DomainNotFoundException("fileId",input.getFileId());
        }
        file.update(input.getImgPath());
        FileOutput output = new FileOutput(input.getFileId(),file.getProduct().getProductId(),input.getImgPath());
        return output;
    }
    @Transactional
    public int deleteFile(Long id){
        File file = fileRepository.findByFileId(id);
        if(file==null){
            throw new DomainNotFoundException("fileId " , id);
        }
        return fileRepository.deleteByFileId(id);
    }
}
