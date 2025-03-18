package br.com.meetime.hubspotintegration.controller;

import br.com.meetime.hubspotintegration.model.WebhookEvent;
import br.com.meetime.hubspotintegration.service.ContactServiceLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class WebhookControllerTest {

    @Mock
    private ContactServiceLog contactServiceLog;

    @InjectMocks
    private WebhookController webhookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleContactCreation_withValidEvents_returnsCreatedResponse() throws URISyntaxException {
        List<WebhookEvent> events = Collections.singletonList(new WebhookEvent());
        doNothing().when(contactServiceLog).saveContactsLog(events);

        ResponseEntity<String> response = webhookController.handleContactCreation(events);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Webhook recebido com sucesso!", response.getBody());
        verify(contactServiceLog, times(1)).saveContactsLog(events);
    }

    @Test
    void handleContactCreation_withEmptyEvents_returnsCreatedResponse() throws URISyntaxException {
        List<WebhookEvent> events = Collections.emptyList();
        doNothing().when(contactServiceLog).saveContactsLog(events);

        ResponseEntity<String> response = webhookController.handleContactCreation(events);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Webhook recebido com sucesso!", response.getBody());
        verify(contactServiceLog, times(1)).saveContactsLog(events);
    }

    @Test
    void handleContactCreation_withException_throwsRuntimeException() {
        List<WebhookEvent> events = Collections.singletonList(new WebhookEvent());
        doThrow(new RuntimeException("URISyntaxException")).when(contactServiceLog).saveContactsLog(events);

        assertThrows(RuntimeException.class, () -> webhookController.handleContactCreation(events));
    }
}