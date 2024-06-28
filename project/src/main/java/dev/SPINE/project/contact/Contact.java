package dev.SPINE.project.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.SPINE.project.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_contacts")
public class Contact {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactEmail> emails;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactPhones> phones;
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
}
