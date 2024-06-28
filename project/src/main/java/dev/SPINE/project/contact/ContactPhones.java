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
@Table(name = "contact_phones")
public class ContactPhones {
    @Id
    @GeneratedValue
    private Integer id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="contactId", referencedColumnName = "id", nullable = false)
    private Contact contact;
    private String number;
    private String label;
}
