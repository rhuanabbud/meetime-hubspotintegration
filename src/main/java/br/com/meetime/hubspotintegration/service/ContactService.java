package br.com.meetime.hubspotintegration.service;

import br.com.meetime.hubspotintegration.entity.Contact;
import br.com.meetime.hubspotintegration.model.WebhookEvent;
import br.com.meetime.hubspotintegration.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveContacts(List<WebhookEvent> events) {
        List<Contact> contacts = events.stream()
                .map(event -> {
                    Contact contact = new Contact();
                    contact.setObjectId(event.getObjectId());
                    contact.setPropertyName(event.getPropertyName());
                    contact.setPropertyValue(event.getPropertyValue());
                    contact.setChangeSource(event.getChangeSource());
                    contact.setEventId(event.getEventId());
                    contact.setSubscriptionId(event.getSubscriptionId());
                    contact.setPortalId(event.getPortalId());
                    contact.setAppId(event.getAppId());
                    contact.setOccurredAt(event.getOccurredAt());
                    contact.setEventType(event.getEventType());
                    contact.setAttemptNumber(event.getAttemptNumber());
                    return contact;
                })
                .collect(Collectors.toList());

        contactRepository.saveAll(contacts);
    }
}