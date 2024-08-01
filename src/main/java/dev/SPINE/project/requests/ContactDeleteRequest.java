package dev.SPINE.project.requests;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDeleteRequest {
    private Integer id;
}
