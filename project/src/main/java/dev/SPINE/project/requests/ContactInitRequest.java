package dev.SPINE.project.requests;

import dev.SPINE.project.contact.Email;
import dev.SPINE.project.contact.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInitRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Email> emails;
    private List<Phone> phones;
}
