package br.com.meetime.hubspotintegration.service;

import br.com.meetime.hubspotintegration.model.ContactData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class HubSpotServiceTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HubSpotService hubSpotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        hubSpotService = new HubSpotService(restTemplateBuilder);
    }

    @Test
    void exchangeCodeForToken_returnsAccessToken() {
        String code = "testCode";
        Map<String, Object> responseBody = Map.of("access_token", "testToken");
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        String token = hubSpotService.exchangeCodeForToken(code);

        assertEquals("testToken", token);
    }

    @Test
    void exchangeCodeForToken_handlesNullResponse() {
        String code = "testCode";
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        assertThrows(NullPointerException.class, () -> hubSpotService.exchangeCodeForToken(code));
    }

    @Test
    void createContact_sendsRequestSuccessfully() {
        String token = "Bearer testToken";
        ContactData contactData = new ContactData();
        contactData.setFirstName("John");
        contactData.setLastName("Doe");
        contactData.setEmail("john.doe@example.com");

        hubSpotService.createContact(token, contactData);

        verify(restTemplate, times(1)).postForEntity(anyString(), any(HttpEntity.class), eq(String.class));
    }
}