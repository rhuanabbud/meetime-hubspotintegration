package br.com.meetime.hubspotintegration.controller;

import br.com.meetime.hubspotintegration.service.HubSpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Value("${hubspot.client.id}")
    private String clientId;

    @Value("${hubspot.redirect.uri}")
    private String redirectUri;

    private final HubSpotService hubSpotService;

    public AuthController(HubSpotService hubSpotService) {
        this.hubSpotService = hubSpotService;
    }

    @GetMapping("/authorize")
    public String getAuthorizationUrl() {
        logger.info("Pegando link de autorizacao do metodo getAuthorizationUrl");
        return "https://app.hubspot.com/oauth/authorize?client_id=" + clientId +
                "&scope=crm.objects.contacts.read%20crm.objects.contacts.write" +
                "&redirect_uri=" + redirectUri;
    }

    @GetMapping("/callback")
    public ResponseEntity<String> handleCallback(@RequestParam("code") String code) {
        logger.info("Pegando code de autorizacao do metodo handleCallback");
        String accessToken = hubSpotService.exchangeCodeForToken(code);
        logger.info("Retornando o Access Token do metodo handleCallback");
        return ResponseEntity.ok("Access Token: " + accessToken);
    }

    @PostMapping("/create-contact")
    public ResponseEntity<String> createContact(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> contactData) throws URISyntaxException {
        logger.info("Iniciando o metodo createContact");
        hubSpotService.createContact(token, contactData);
        logger.info("Finalizando o metodo createContact");
        return ResponseEntity.created(new URI("/auth/create-contact")).body("Contato foi criado com sucesso!");
    }
}