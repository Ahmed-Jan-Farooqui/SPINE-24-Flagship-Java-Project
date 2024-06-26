package dev.SPINE.project.controllers;

import dev.SPINE.project.requests.AuthRequest;
import dev.SPINE.project.responses.AuthResponse;
import dev.SPINE.project.requests.SignupRequest;
import dev.SPINE.project.auth.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignupService signupService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody SignupRequest signupRequest){
        var response = signupService.signUp(signupRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        var response = signupService.signIn(authRequest);
        return ResponseEntity.ok(response);
    }
}
