package com.ssafy.market.domain.user.repository;

import com.ssafy.market.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findByProviderId(Long providerId);
//    Optional<User> findById(Long id);
//    Optional<User> findById(Long userId);
    Boolean existsByEmail(String email);
}
