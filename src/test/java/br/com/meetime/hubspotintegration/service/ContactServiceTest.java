package br.com.meetime.hubspotintegration.service;

import br.com.meetime.hubspotintegration.entity.Contact;
import br.com.meetime.hubspotintegration.model.ContactData;
import br.com.meetime.hubspotintegration.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveContacts_savesContactSuccessfully() {
        ContactData contactData = new ContactData();
        contactData.setFirstName("John");
        contactData.setLastName("Doe");
        contactData.setEmail("john.doe@example.com");

        contactService.saveContacts(contactData);

        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void saveContacts_handlesEmptyContactData() {
        ContactData contactData = new ContactData();

        contactService.saveContacts(contactData);

        verify(contactRepository, times(1)).save(any(Contact.class));
    }
}