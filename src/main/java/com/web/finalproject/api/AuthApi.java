package com.web.finalproject.api;

import com.web.finalproject.config.JwtUtils;
import com.web.finalproject.model.TokenRequest;
import com.web.finalproject.model.TokenResponse;
import com.web.finalproject.model.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class AuthApi {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody TokenRequest tokenRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserAdapter userAdapter = (UserAdapter) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userAdapter.getUsername());
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
            tokenResponse.setExpired(new Date().getTime() + jwtUtils.getExpiration());
            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokenResponse());
        }
    }
}
