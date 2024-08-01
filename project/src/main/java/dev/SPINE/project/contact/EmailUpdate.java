package dev.SPINE.project.contact;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailUpdate {
    private ContactEmail email;
    private String requestType;
}
