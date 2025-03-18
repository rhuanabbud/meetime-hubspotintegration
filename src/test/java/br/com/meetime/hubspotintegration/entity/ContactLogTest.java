package br.com.meetime.hubspotintegration.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactLogTest {

    @Test
    void contactLogCreation_setsAllFieldsCorrectly() {
        ContactLog contactLog = new ContactLog();
        contactLog.setObjectId(123L);
        contactLog.setChangeSource("API");
        contactLog.setEventId(456L);
        contactLog.setSubscriptionId(789);
        contactLog.setPortalId(101);
        contactLog.setAppId(202);
        contactLog.setOccurredAt(303L);
        contactLog.setAttemptNumber(1);

        assertEquals(123L, contactLog.getObjectId());
        assertEquals("API", contactLog.getChangeSource());
        assertEquals(456L, contactLog.getEventId());
        assertEquals(789, contactLog.getSubscriptionId());
        assertEquals(101, contactLog.getPortalId());
        assertEquals(202, contactLog.getAppId());
        assertEquals(303L, contactLog.getOccurredAt());
        assertEquals(1, contactLog.getAttemptNumber());
    }

    @Test
    void contactLogCreation_withNullValues() {
        ContactLog contactLog = new ContactLog();
        contactLog.setObjectId(null);
        contactLog.setChangeSource(null);
        contactLog.setEventId(null);
        contactLog.setSubscriptionId(null);
        contactLog.setPortalId(null);
        contactLog.setAppId(null);
        contactLog.setOccurredAt(null);
        contactLog.setAttemptNumber(null);

        assertNull(contactLog.getObjectId());
        assertNull(contactLog.getChangeSource());
        assertNull(contactLog.getEventId());
        assertNull(contactLog.getSubscriptionId());
        assertNull(contactLog.getPortalId());
        assertNull(contactLog.getAppId());
        assertNull(contactLog.getOccurredAt());
        assertNull(contactLog.getAttemptNumber());
    }

    @Test
    void contactLogCreation_setsIdCorrectly() {
        ContactLog contactLog = new ContactLog();
        contactLog.setId(1L);

        assertEquals(1L, contactLog.getId());
    }

    @Test
    void contactLogCreation_objectIdIsNotNull() {
        ContactLog contactLog = new ContactLog();
        contactLog.setObjectId(123L);

        assertNotNull(contactLog.getObjectId());
    }
}