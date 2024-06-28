package dev.SPINE.project.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactPhoneRepository extends JpaRepository<ContactPhones, Integer> {
    List<ContactPhones> findAllByContact(Contact contact);
}
