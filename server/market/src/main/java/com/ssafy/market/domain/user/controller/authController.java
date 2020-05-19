package com.ssafy.market.domain.user.controller;

import com.ssafy.market.domain.user.domain.AuthProvider;
//import com.project.sogaething.login.domain.User;
//import com.project.sogaething.login.exception.BadRequestException;
//import com.project.sogaething.login.payload.ApiResponse;
//import com.project.sogaething.login.payload.AuthResponse;
//import com.project.sogaething.login.payload.LoginRequest;
//import com.project.sogaething.login.payload.SignUpRequest;
//import com.project.sogaething.login.repository.UserRepository;
//import com.project.sogaething.login.security.TokenProvider;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.exception.BadRequestException;
import com.ssafy.market.domain.user.payload.ApiResponse;
import com.ssafy.market.domain.user.payload.AuthResponse;
import com.ssafy.market.domain.user.payload.LoginRequest;
import com.ssafy.market.domain.user.payload.SignUpRequest;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class authController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }
//    private tokenController kakao_restapi = new tokenController();
//
//    @RequestMapping(value = "/oauth", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
//    public String kakaoLogin(@RequestParam("code") String code) {
//        System.out.println(access_token);
//        return "home";
//    }

}
