package dev.SPINE.project.requests;

import dev.SPINE.project.contact.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactUpdateRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<PhoneUpdate> phoneReqs;
    private List<EmailUpdate> emailReqs;
}
