package br.com.meetime.hubspotintegration.controller;

import br.com.meetime.hubspotintegration.model.WebhookEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    public WebhookController() {
    }

    @PostMapping("/contact-creation")
    public ResponseEntity<String> handleContactCreation(@RequestBody List<WebhookEvent> events) throws URISyntaxException {
        return ResponseEntity.created(new URI("/webhook/contact-creation")).body("Webhook recebido com sucesso!");
    }
}