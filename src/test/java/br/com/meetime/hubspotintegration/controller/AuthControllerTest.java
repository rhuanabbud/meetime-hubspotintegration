package br.com.meetime.hubspotintegration.controller;

import br.com.meetime.hubspotintegration.model.ContactData;
import br.com.meetime.hubspotintegration.service.ContactService;
import br.com.meetime.hubspotintegration.service.HubSpotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private HubSpotService hubSpotService;

    @Mock
    private ContactService contactService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAuthorizationUrl_returnsCorrectUrl() throws NoSuchFieldException, IllegalAccessException {
        String expectedUrl = "https://app.hubspot.com/oauth/authorize?client_id=test-client-id&scope=crm.objects.contacts.read%20crm.objects.contacts.write&redirect_uri=test-redirect-uri";

        Field clientIdField = AuthController.class.getDeclaredField("clientId");
        clientIdField.setAccessible(true);
        clientIdField.set(authController, "test-client-id");

        Field redirectUriField = AuthController.class.getDeclaredField("redirectUri");
        redirectUriField.setAccessible(true);
        redirectUriField.set(authController, "test-redirect-uri");

        String actualUrl = authController.getAuthorizationUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    void handleCallback_returnsAccessToken() {
        String code = "test-code";
        String accessToken = "test-access-token";
        when(hubSpotService.exchangeCodeForToken(code)).thenReturn(accessToken);

        ResponseEntity<String> response = authController.handleCallback(code);

        assertEquals("Access Token: " + accessToken, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void createContact_createsContactSuccessfully() throws URISyntaxException {
        String token = "Bearer test-token";
        ContactData contactData = new ContactData();
        doNothing().when(hubSpotService).createContact(token, contactData);
        doNothing().when(contactService).saveContacts(contactData);

        ResponseEntity<String> response = authController.createContact(token, contactData);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Contato foi criado com sucesso!", response.getBody());
        verify(hubSpotService, times(1)).createContact(token, contactData);
        verify(contactService, times(1)).saveContacts(contactData);
    }

    @Test
    void createContact_throwsRuntimeException() {
        String token = "Bearer test-token";
        ContactData contactData = new ContactData();
        doThrow(new RuntimeException("URISyntaxException")).when(hubSpotService).createContact(token, contactData);

        assertThrows(RuntimeException.class, () -> authController.createContact(token, contactData));
    }
}