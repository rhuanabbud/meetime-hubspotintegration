package br.com.meetime.hubspotintegration.service;

import br.com.meetime.hubspotintegration.entity.Contact;
import br.com.meetime.hubspotintegration.model.ContactData;
import br.com.meetime.hubspotintegration.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveContacts(ContactData contactData) {
        logger.info("Iniciando metodo saveContacts");

        Contact contact = new Contact();
        contact.setFirstName(contactData.getFirstName());
        contact.setLastName(contactData.getLastName());
        contact.setEmail(contactData.getEmail());

        contactRepository.save(contact);
        logger.info("Finalizado metodo saveContactsLog");
    }
}