package br.com.meetime.hubspotintegration.repository;

import br.com.meetime.hubspotintegration.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}