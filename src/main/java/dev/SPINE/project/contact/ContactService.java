package dev.SPINE.project.contact;

import dev.SPINE.project.requests.ContactDeleteRequest;
import dev.SPINE.project.requests.ContactInitRequest;
import dev.SPINE.project.responses.ContactInitResponse;
import dev.SPINE.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactInitResponse save(ContactInitRequest request) {
        var email = request.getUserEmail();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var contact = Contact.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .user(user)
                .build();
        var emails = request.getEmails().stream().map((e) ->
                ContactEmail
                        .builder()
                        .label(e.getLabel())
                        .contact(contact)
                        .email(e.getEmail())
                        .build()).toList();
        contact.setEmails(emails);
        contactRepository.save(contact);
        return ContactInitResponse.builder().id(contact.getId()).build();
    }

    public String delete(ContactDeleteRequest request) {
        try {
            contactRepository.deleteById(request.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Contact deleted successfully.";
    }
}
