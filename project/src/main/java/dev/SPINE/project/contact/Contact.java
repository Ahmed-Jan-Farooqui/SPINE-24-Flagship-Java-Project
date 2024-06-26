package dev.SPINE.project.contact;

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
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
    // Add email and phones here later.
    @Setter
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactEmail> emails;
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;

}
