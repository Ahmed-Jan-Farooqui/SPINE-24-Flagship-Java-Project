package dev.SPINE.project.contact;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneUpdate {
    private ContactPhones phone;
    private String requestType;
}

