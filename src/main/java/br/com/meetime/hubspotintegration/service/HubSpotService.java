package br.com.meetime.hubspotintegration.service;

import br.com.meetime.hubspotintegration.model.ContactData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
public class HubSpotService {

    private static final Logger logger = LoggerFactory.getLogger(HubSpotService.class);

    @Value("${hubspot.client.id}")
    private String clientId;

    @Value("${hubspot.client.secret}")
    private String clientSecret;

    @Value("${hubspot.redirect.uri}")
    private String redirectUri;

    private final RestTemplate restTemplate;

    public HubSpotService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String exchangeCodeForToken(String code) {

        logger.info("Iniciando metodo exchangeCodeForToken para geracao do token a partir do code");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("redirect_uri", redirectUri);
        requestBody.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "https://api.hubapi.com/oauth/v1/token",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        logger.info("Finalizando metodo exchangeCodeForToken para geracao do token apartir do code");

        return (String) Objects.requireNonNull(response.getBody()).get("access_token");
    }

    public void createContact(String token, ContactData contactData) {

        logger.info("Iniciando metodo createContact para criacao do contato");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContactData> entity = new HttpEntity<>(contactData, headers);

        logger.debug("Enviando a requisicao para HubSpot API contact data: {}", contactData);

        restTemplate.postForEntity(
                "https://api.hubapi.com/crm/v3/objects/contacts",
                entity,
                String.class
        );

        logger.info("Finalizando metodo createContact para criacao do contato");
    }
}