package dev.SPINE.project.requests;

import dev.SPINE.project.contact.Contact;
import dev.SPINE.project.contact.Email;
import dev.SPINE.project.contact.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactUpdateRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private Phone phone;
    private Integer phoneId; // For updating a specific phone.
    private Email email;
    private Integer emailId; // For updating a specific email.
}
