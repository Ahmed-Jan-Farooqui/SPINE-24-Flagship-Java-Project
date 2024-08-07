package dev.SPINE.project.contact;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact_emails")
public class ContactEmail {
    @Id
    @GeneratedValue
    private Integer id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="contactId", referencedColumnName = "id", nullable = false)
    private Contact contact;
    private String email;
    private String label;
}
