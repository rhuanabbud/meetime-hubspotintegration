package br.com.meetime.hubspotintegration.controller;

import br.com.meetime.hubspotintegration.service.HubSpotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

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
        return "https://app.hubspot.com/oauth/authorize?client_id=" + clientId +
                "&scope=crm.objects.contacts.read%20crm.objects.contacts.write" +
                "&redirect_uri=" + redirectUri;
    }

    @GetMapping("/callback")
    public ResponseEntity<String> handleCallback(@RequestParam("code") String code) {
        String accessToken = hubSpotService.exchangeCodeForToken(code);
        return ResponseEntity.ok("Access Token: " + accessToken);
    }

    @PostMapping("/create-contact")
    public ResponseEntity<String> createContact(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> contactData) throws URISyntaxException {
        hubSpotService.createContact(token, contactData);
        return ResponseEntity.created(new URI("/auth/create-contact")).body("Contato foi criado com sucesso!");
    }
}