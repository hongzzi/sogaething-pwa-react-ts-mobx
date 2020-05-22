package com.ssafy.market.domain.file.repository;

import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

}
