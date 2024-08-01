package dev.SPINE.project.contact;

import dev.SPINE.project.requests.ContactDeleteRequest;
import dev.SPINE.project.requests.ContactInitRequest;
import dev.SPINE.project.requests.ContactUpdateRequest;
import dev.SPINE.project.responses.ContactInitResponse;
import dev.SPINE.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ContactEmailRepository contactEmailRepository;
    private final ContactPhoneRepository contactPhoneRepository;


    public ContactInitResponse save(ContactInitRequest request) {
        var id = request.getId();
        var user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var contact = Contact.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .user(user)
                .build();
        var emails = request.getEmails().stream().map(e ->
                ContactEmail
                        .builder()
                        .label(e.getLabel())
                        .contact(contact)
                        .email(e.getEmail())
                        .build()).toList();
        var phones = request.getPhones().stream().map(p ->
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
            throw new UsernameNotFoundException("Could not find a contact with the corresponding contact ID.");
        }
        return "Contact deleted successfully.";
    }

    public String update(ContactUpdateRequest request){
        var contact = contactRepository.findById(request.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Contact not found"));

        if (!Objects.equals(request.getFirstName(), "")){
            contact.setFirstName(request.getFirstName());
        }

        if (!Objects.equals(request.getLastName(), "")){
            contact.setLastName(request.getLastName());
        }

        if (!request.getEmailReqs().isEmpty()){
            var emailReqs = request.getEmailReqs();
            for (EmailUpdate emailReq : emailReqs) {
                ContactEmail emailObj;
                if (Objects.equals(emailReq.getRequestType(), "update")) {
                    emailObj = contactEmailRepository.findById(emailReq.getEmail().getId())
                            .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
                    emailObj.setEmail(emailReq.getEmail().getEmail());
                    emailObj.setLabel(emailReq.getEmail().getLabel());
                    contactEmailRepository.save(emailObj);
                } else if (Objects.equals(emailReq.getRequestType(), "add")) {
                    emailObj = ContactEmail
                            .builder()
                            .email(emailReq.getEmail().getEmail())
                            .label(emailReq.getEmail().getLabel())
                            .contact(contact)
                            .build();
                    contactEmailRepository.save(emailObj);
                } else {
                    contactEmailRepository.deleteById(emailReq.getEmail().getId());
                }
            }
        }

        if (!request.getPhoneReqs().isEmpty()) {
            var phoneReqs = request.getPhoneReqs();
            for (PhoneUpdate phoneReq : phoneReqs) {
                ContactPhones phoneObj;
                if (Objects.equals(phoneReq.getRequestType(), "update")) {
                    phoneObj = contactPhoneRepository.findById(phoneReq.getPhone().getId())
                            .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
                    phoneObj.setNumber(phoneReq.getPhone().getNumber());
                    phoneObj.setLabel(phoneReq.getPhone().getLabel());
                    contactPhoneRepository.save(phoneObj);
                } else if (Objects.equals(phoneReq.getRequestType(), "add")) {
                    phoneObj = ContactPhones
                            .builder()
                            .number(phoneReq.getPhone().getNumber())
                            .label(phoneReq.getPhone().getLabel())
                            .contact(contact)
                            .build();
                    contactPhoneRepository.save(phoneObj);
                } else {
                    contactPhoneRepository.deleteById(phoneReq.getPhone().getId());
                }
            }
        }
        return "Contact updated successfully.";
    }

    public Page<Contact> search(Integer userId, String keyword, Pageable pageable){
        return contactRepository.findAllByKeyword(userId, keyword, pageable);
    }

    public Page<Contact> getContacts(Integer userId, Pageable pageable){
        return contactRepository.findAllByUserId(userId, pageable);
    }
}
