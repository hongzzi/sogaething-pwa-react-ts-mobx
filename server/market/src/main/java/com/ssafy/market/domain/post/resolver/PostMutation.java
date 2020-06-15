package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.dto.*;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;

import com.ssafy.market.domain.user.security.TokenProvider;
import com.ssafy.market.global.apis.NewImageApi;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final HashtagRepository hashtagRepository;
    private final FileRepository fileRepository;
    private final TokenProvider tokenProvider;
    private final NewImageApi apis;


    @Transactional
    public Output createPost(CreatePostInput input, DataFetchingEnvironment env) {

        Long userId = tokenProvider.getUserIdFromHeader(env);
        User user = (userRepository.findByUserId(userId));

        Output output = null;
        Boolean check = true;
        Post post = null;
        Product product = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar cal = Calendar.getInstance();
            String today = null;
            today = formatter.format(cal.getTime());
            Timestamp ts = Timestamp.valueOf(today);
            if (input.getTitle().equals("") || input.getContents().equals("") || input.getTransaction().equals("") || input.getPrice() == null || input.getCategory().equals("")) {
                check = false;
            }
            if (input.getHashtag().length < 0 || input.getImgPaths().length < 0) {
                check = false;
            }
            if (check) {
                post = postRepository.save(new Post(null, user, false, input.getTitle(), ts, input.getContents(), (long) 0, "판매", "진행중", input.getTransaction()));
                product = productRepository.save(new Product(null, post, input.getPrice(), input.getCategory(), (long) 0));
                String[] hashtagArr = input.getHashtag();
                if (hashtagArr.length == 0) {
                    System.out.println("들어온 해시태그 데이터 없음..");
                    check = false;
                }else {
                    List<Hashtag> hList = new ArrayList<>();
                    for (int i = 0; i < hashtagArr.length; i++) {
                        hList.add(new Hashtag(null, product, hashtagArr[i]));
                    }
                    hashtagRepository.saveAll(hList);
                }
                String[] Arr = input.getImgPaths();
                List<File> fList = new ArrayList<>();
                if (Arr.length == 0){
                    System.out.println("들어온 파일 데이타가 없음");
                    check = false;
                }
                else {
                    for (int k = 0; k < Arr.length; k++) {
                        String[] temp = Arr[k].split(",");
                        String imgur = apis.uploadImg(temp[1]);
                        System.out.println(imgur);
                        if (!imgur.equals("false")) {
                            fList.add(new File(null, product, imgur));
                        } else {
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        fileRepository.saveAll(fList);
                    }
                }
                output = new Output("SUCCESS", post.getPostId());
            } else {
                check = false;
            }
        } catch (Exception e) {
            output = new Output("FAIL", null);
//            System.out.println(e);
            e.printStackTrace();
            return output;
        }
        if (!check) {
            System.out.println("어느 하나가 없다는 애기");
            output = new Output("FAIL", null);
            if (post != null) {
                postRepository.deleteByPostId(post.getPostId());
            }
            if (product != null) {
                productRepository.deleteByProductId(product.getProductId());
            }
            List<Hashtag> list = hashtagRepository.findByProduct(product);
            if (list.size() > 0) {
                for (int j = 0; j < list.size(); j++) {
                    hashtagRepository.deleteByHashtagId(list.get(j).getHashtagId());
                }
            }
        }
        return output;
    }

    @Transactional
    public PostMetaOutput updatePost(UpdatePostInput input) {
        Post post = postRepository.findByPostId(input.getPostId());
        post.update(input.getTitle(), input.getContents(), input.getTransaction());
        Product product = productRepository.findByPost(post);
        product.update(post, input.getPrice(), input.getCategory());
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
        List<File> fileList = fileRepository.findByProduct(product);
        // 해시 태그 및 파일 삭제 하기
        for (int i = 0; i < fileList.size(); i++) {
            fileRepository.deleteByFileId(fileList.get(i).getFileId());
        }
        for (int j = 0; j < hashtagList.size(); j++) {
            hashtagRepository.deleteByHashtagId(hashtagList.get(j).getHashtagId());
        }
        // 해시 태그 및 파일 추가 하기
        String[] hashtagArr = input.getHashtag();
        List<Hashtag> hList = new ArrayList<>();
        for (int i = 0; i < hashtagArr.length; i++) {
            hList.add(new Hashtag(null, product, hashtagArr[i]));
        }
        hashtagRepository.saveAll(hList);

        String[] fArr = input.getImgPaths();
        List<File> fList = new ArrayList<>();
        if (fArr.length > 0) {
            for (int k = 0; k < fArr.length; k++) {
                String[] temp = fArr[k].split(",");
                String imgur = apis.uploadImg(temp[1]);
                if (!imgur.equals("false")) {
                    fList.add(new File(null, product, imgur));
                } else {
                    break;
                }
            }
        }
        fileRepository.saveAll(fList);
        List<String> hash = hashtagRepository.findDistinctByHashtag(product.getProductId());
        String imgPath = fileRepository.findTop1ByProduct(product).getImgPath();
        PostMetaOutput output = new PostMetaOutput(
                input.getPostId(), post.getTitle(), product.getCategory(), imgPath,
                product.getPrice(), hash, post.isBuy(), post.getViewCount(), post.getDeal(), post.getDealState(),
                post.getSaleDate().toString(), post.getTransaction(), post.getCreatedDate().toString(),
                post.getModifiedDate().toString());

        return output;
    }

    @Transactional
    public Long updateView(Long postId) {
        Post post = postRepository.findByPostId(postId);
        post.updateViewCount();
        return post.getViewCount();
    }

    @Transactional
    public Boolean updateIsBuy(Long postsId) {
        Post post = postRepository.findByPostId(postsId);
        if (post.isBuy() == true) {
            post.update(false);
        } else {
            post.update(true);
        }
        return post.isBuy();
    }

    @Transactional
    public Output deletePost(Long postId) {
        Output output = null;

        Post post = postRepository.findByPostId(postId);
        Product product = productRepository.findByPost(post);
        List<File> fileList = fileRepository.findByProduct(product);
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);

        try {
            for (int i = 0; i < fileList.size(); i++) {
                fileRepository.deleteByFileId(fileList.get(i).getFileId());
            }
            for (int j = 0; j < hashtagList.size(); j++) {
                hashtagRepository.deleteByHashtagId(hashtagList.get(j).getHashtagId());
            }
            productRepository.deleteByProductId(product.getProductId());
            postRepository.deleteByPostId(postId);
            output = new Output("SUCCESS", postId);
        } catch (Exception e) {
            output = new Output("FAIL", null);
        }
        return output;
    }
}
