package dev.SPINE.project.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactEmailRepository extends JpaRepository<ContactEmail, Integer> {
    List<ContactEmail> findAllByContact(Contact contact);
}
