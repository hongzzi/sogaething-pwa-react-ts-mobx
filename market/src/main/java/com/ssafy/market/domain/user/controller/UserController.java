package com.ssafy.market.domain.user.controller;

//import com.project.sogaething.login.domain.User;
//import com.project.sogaething.login.exception.ResourceNotFoundException;
//import com.project.sogaething.login.repository.UserRepository;
//import com.project.sogaething.login.security.CurrentUser;
//import com.project.sogaething.login.security.UserPrincipal;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.exception.ResourceNotFoundException;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.CurrentUser;
import com.ssafy.market.domain.user.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
            return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
