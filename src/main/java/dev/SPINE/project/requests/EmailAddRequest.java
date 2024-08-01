package dev.SPINE.project.requests;

import dev.SPINE.project.contact.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailAddRequest {
    private Email email;
}
