package dev.SPINE.project.controllers;

import dev.SPINE.project.requests.AuthRequest;
import dev.SPINE.project.responses.AuthResponse;
import dev.SPINE.project.requests.SignupRequest;
import dev.SPINE.project.auth.SignupService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignupService signupService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody SignupRequest signupRequest){
        logger.info("Signup request received from: {}", signupRequest.getEmail());
        var response = signupService.signUp(signupRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        logger.info("Signin request received from: {}", authRequest.getEmail());
        var response = signupService.signIn(authRequest);
        return ResponseEntity.ok(response);
    }
}
