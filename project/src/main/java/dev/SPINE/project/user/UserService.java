package dev.SPINE.project.user;


import dev.SPINE.project.contact.ContactRepository;
import dev.SPINE.project.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    public UserDTO getUserById(Integer id, Pageable pageable) {
        var user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User does not exist."));
        var contacts = contactRepository.findAllByUserId(id, pageable);
        return UserDTO.builder()
                .email(user.getUsername())
                .username(user.getUsername())
                .contacts(contacts)
                .build();
    }
}
