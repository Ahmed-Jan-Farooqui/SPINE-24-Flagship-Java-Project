package dev.SPINE.project.controllers;


import dev.SPINE.project.requests.PasswordChangeRequest;
import dev.SPINE.project.user.UserRepository;
import dev.SPINE.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest request){
        var response = userService.changePassword(request.getId(), request.getCurrentPassword(), request.getNewPassword());
        return ResponseEntity.ok(response);
    }
}
