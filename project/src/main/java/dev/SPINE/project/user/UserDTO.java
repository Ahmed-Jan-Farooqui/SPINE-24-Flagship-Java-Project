package dev.SPINE.project.user;

import dev.SPINE.project.contact.Contact;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
public class UserDTO {
    private String username;
    private String email;
    private Page<Contact> contacts;
    private static final String ROLE = "ROLE_USER";
}
