package dev.SPINE.project.contact;

import dev.SPINE.project.requests.ContactDeleteRequest;
import dev.SPINE.project.requests.ContactInitRequest;
import dev.SPINE.project.requests.ContactUpdateRequest;
import dev.SPINE.project.responses.ContactInitResponse;
import dev.SPINE.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ContactEmailRepository contactEmailRepository;
    private final ContactPhoneRepository contactPhoneRepository;

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
        var phones = request.getPhones().stream().map((p) ->
            ContactPhones
                    .builder()
                    .label(p.getLabel())
                    .contact(contact)
                    .number(p.getNumber())
                    .build()
        ).toList();
        contact.setEmails(emails);
        contact.setPhones(phones);
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

    public String update(ContactUpdateRequest request){
        var contact = contactRepository.findById(request.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Contact not found"));

        if (request.getFirstName() != null){
            contact.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null){
            contact.setLastName(request.getFirstName());
        }

        if (request.getEmail() != null){
            var email = request.getEmail();
            ContactEmail email_obj;
            if (request.getEmailId() != null){
                email_obj = contactEmailRepository.findById(request.getEmailId())
                        .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
                email_obj.setEmail(email.getEmail());
                email_obj.setLabel(email.getLabel());
            }
            else {
                email_obj = ContactEmail
                        .builder()
                        .email(email.getEmail())
                        .label(email.getLabel())
                        .contact(contact)
                        .build();
            }
            contactEmailRepository.save(email_obj);
        }

        if (request.getPhone() != null) {
            var phone = request.getPhone();
            ContactPhones phone_obj;
            if (request.getPhoneId() != null){
                phone_obj = contactPhoneRepository.findById(request.getPhoneId())
                        .orElseThrow(() -> new UsernameNotFoundException("Phone not found"));
                phone_obj.setNumber(phone.getNumber());
                phone_obj.setLabel(phone.getLabel());
            }
            else {
                phone_obj = ContactPhones
                    .builder()
                    .label(phone.getLabel())
                    .contact(contact)
                    .number(phone.getNumber())
                    .build();
            }
            contactPhoneRepository.save(phone_obj);
        }
        return "Contact updated successfully.";
    }
}
