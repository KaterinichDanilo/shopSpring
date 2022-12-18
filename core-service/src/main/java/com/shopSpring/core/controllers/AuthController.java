package com.shopSpring.core.controllers;

import com.shopSpring.core.dto.JwtRequest;
import com.shopSpring.core.dto.JwtResponse;
import com.shopSpring.core.entities.User;
import com.shopSpring.core.services.UserService;
import com.shopSpring.core.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            System.out.println(authRequest.getLogin());
            System.out.println(authRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getLogin());
        String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/reg")
    public void createRegToken(@RequestBody JwtRequest authRequest) {
        User user = userService.findByUsername(authRequest.getLogin()).orElse(null);
        System.out.println(authRequest.getLogin());
        System.out.println(authRequest.getPassword());
        if (user != null) {
            System.out.println("user un null");
            return;
        }
        user = userService.findByEmail(authRequest.getEmail()).orElse(null);
        if (user != null) {
            System.out.println("User em null");
            return;
        }
        userService.saveUser(authRequest.getLogin(), authRequest.getPassword(),
                authRequest.getFirstName(), user.getLastName(), authRequest.getEmail());
    }
}
