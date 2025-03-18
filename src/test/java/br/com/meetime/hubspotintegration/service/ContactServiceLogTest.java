package br.com.meetime.hubspotintegration.service;

import br.com.meetime.hubspotintegration.entity.ContactLog;
import br.com.meetime.hubspotintegration.model.WebhookEvent;
import br.com.meetime.hubspotintegration.repository.ContactLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ContactServiceLogTest {

    @Mock
    private ContactLogRepository contactLogRepository;

    @InjectMocks
    private ContactServiceLog contactServiceLog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveContactsLog_savesAllContactsSuccessfully() {
        WebhookEvent event = new WebhookEvent();
        event.setObjectId(1L);
        event.setChangeSource("API");
        event.setEventId(1L);
        event.setSubscriptionId(1);
        event.setPortalId(1);
        event.setAppId(1);
        event.setOccurredAt(1L);
        event.setAttemptNumber(1);

        List<WebhookEvent> events = List.of(event);

        contactServiceLog.saveContactsLog(events);

        verify(contactLogRepository, times(1)).saveAll(anyList());
    }
}