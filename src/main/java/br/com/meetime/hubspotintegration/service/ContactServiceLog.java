package br.com.meetime.hubspotintegration.service;

import br.com.meetime.hubspotintegration.entity.ContactLog;
import br.com.meetime.hubspotintegration.model.WebhookEvent;
import br.com.meetime.hubspotintegration.repository.ContactLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceLog {

    private static final Logger logger = LoggerFactory.getLogger(ContactServiceLog.class);

    private final ContactLogRepository contactLogRepository;

    public ContactServiceLog(ContactLogRepository contactLogRepository) {
        this.contactLogRepository = contactLogRepository;
    }

    public void saveContactsLog(List<WebhookEvent> events) {
        logger.info("Iniciando metodo saveContactsLog");
        List<ContactLog> contacts = events.stream()
                .map(event -> {
                    logger.info("Salvando contato: {}", event.toString());
                    ContactLog contact = new ContactLog();
                    contact.setObjectId(event.getObjectId());
                    contact.setChangeSource(event.getChangeSource());
                    contact.setEventId(event.getEventId());
                    contact.setSubscriptionId(event.getSubscriptionId());
                    contact.setPortalId(event.getPortalId());
                    contact.setAppId(event.getAppId());
                    contact.setOccurredAt(event.getOccurredAt());
                    contact.setAttemptNumber(event.getAttemptNumber());
                    return contact;
                })
                .collect(Collectors.toList());

        contactLogRepository.saveAll(contacts);
        logger.info("Finalizado metodo saveContactsLog");
    }
}