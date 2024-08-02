package dev.SPINE.project.user;


import dev.SPINE.project.config.AppConfig;
import dev.SPINE.project.contact.ContactRepository;
import dev.SPINE.project.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final AppConfig appConfig;

    public UserDTO getUserById(Integer id, Pageable pageable) {
        var user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User does not exist."));
        var contacts = contactRepository.findAllByUserId(id, pageable);
        return UserDTO.builder()
                .email(user.getUsername())
                .username(user.getUsername())
                .contacts(contacts)
                .build();
    }

    public String changePassword(Integer id, String oldPassword, String newPassword){
        var user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(("User does not exist.")));
        var encoder = appConfig.passwordEncoder();
        var returnString = "Error occurred when setting new password.";
        var password = user.getPassword();
        if (encoder.matches(oldPassword, password)){
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            returnString = "Password updated successfully";
        }
        return returnString;
    }
}
