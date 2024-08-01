package dev.SPINE.project.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Page<Contact> findAllByUserId(Integer userId, Pageable pageable);
    @Query("SELECT c FROM Contact c LEFT JOIN c.emails e LEFT JOIN c.phones p " +
            "WHERE c.user.id = :userId AND (" +
            "LOWER(c.firstName) LIKE LOWER(%:keyword%)" +
            " OR LOWER(c.lastName) LIKE LOWER(%:keyword%)" +
            " OR LOWER(e.email) LIKE LOWER(%:keyword%)" +
            " OR LOWER(p.number) LIKE LOWER(%:keyword%))")
    Page<Contact> findAllByKeyword(@Param("userId") Integer userId, @Param("keyword") String keyword, Pageable pageable);
}
