package com.ssafy.market.domain.file.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.dto.FileOutput;
import com.ssafy.market.domain.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileQuery implements GraphQLQueryResolver {
    private final FileRepository fileRepository;

    public List<FileOutput> findAllFile() {
        List<FileOutput> fileOutputList = new ArrayList<>();
        List<File> fileList = fileRepository.findAll();
        for(int i =0; i<fileList.size(); i++){
            fileOutputList.add(new FileOutput(fileList.get(i).getFileId(),fileList.get(i).getProduct().getProductId(),fileList.get(i).getImgPath()));
        }
        return fileOutputList;
    }
    public Iterable<File> findAllFiles(){
        return fileRepository.findAll();
    }
    public FileOutput findFileById(Long id) {
        File file = fileRepository.findByFileId(id);
        FileOutput output = new FileOutput(id,file.getProduct().getProductId(), file.getImgPath());
        return output;
    }

}