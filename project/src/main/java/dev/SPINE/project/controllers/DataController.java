package dev.SPINE.project.controllers;


import dev.SPINE.project.requests.GetUserRequest;
import dev.SPINE.project.user.User;
import dev.SPINE.project.user.UserRepository;
import dev.SPINE.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {
    private final UserService userService;
    @GetMapping("/user")
    public User getUser(@RequestBody GetUserRequest request) {
        return userService.getUserByEmail(request);
    }
}
