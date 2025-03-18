package br.com.meetime.hubspotintegration.repository;

import br.com.meetime.hubspotintegration.entity.ContactLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactLogRepository extends JpaRepository<ContactLog, Long> {
}