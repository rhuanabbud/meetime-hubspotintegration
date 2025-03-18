package br.com.meetime.hubspotintegration.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void contactCreation_setsAllFieldsCorrectly() {
        Contact contact = new Contact();
        contact.setFirstName("Rhuan");
        contact.setLastName("Silva");
        contact.setEmail("rhuan.silva@examplo.com");
        LocalDateTime now = LocalDateTime.now();
        contact.setDataInclusao(now);

        assertEquals("Rhuan", contact.getFirstName());
        assertEquals("Silva", contact.getLastName());
        assertEquals("rhuan.silva@examplo.com", contact.getEmail());
        assertEquals(now, contact.getDataInclusao());
    }

    @Test
    void contactCreation_withNullValues() {
        Contact contact = new Contact();
        contact.setFirstName(null);
        contact.setLastName(null);
        contact.setEmail(null);
        LocalDateTime now = LocalDateTime.now();
        contact.setDataInclusao(now);

        assertNull(contact.getFirstName());
        assertNull(contact.getLastName());
        assertNull(contact.getEmail());
        assertEquals(now, contact.getDataInclusao());
    }

    @Test
    void contactCreation_setsIdCorrectly() {
        Contact contact = new Contact();
        contact.setId(1L);

        assertEquals(1L, contact.getId());
    }

    @Test
    void contactCreation_dataInclusaoIsNotNull() {
        Contact contact = new Contact();
        contact.setDataInclusao(LocalDateTime.now());

        assertNotNull(contact.getDataInclusao());
    }
}