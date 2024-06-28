package dev.SPINE.project.user;


import dev.SPINE.project.requests.GetUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUserByEmail(GetUserRequest request) {
        return userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User does not exist."));
    }
}
