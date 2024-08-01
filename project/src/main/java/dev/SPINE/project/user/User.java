package dev.SPINE.project.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.SPINE.project.contact.Contact;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Table(name = "_user")
public class User implements UserDetails {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    @Getter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;
    private static final String ROLE = "ROLE_USER";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(ROLE));
    }

    @Override
    public String getPassword() {
        return password;
    }

    // I return email here AS EMAIL IS UNIQUE!
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
