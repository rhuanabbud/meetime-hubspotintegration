package br.com.meetime.hubspotintegration.controller;

import br.com.meetime.hubspotintegration.model.WebhookEvent;
import br.com.meetime.hubspotintegration.service.ContactServiceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    private final ContactServiceLog contactServiceLog;

    public WebhookController(ContactServiceLog contactService) {
        this.contactServiceLog = contactService;
    }

    @PostMapping("/contact-creation")
    public ResponseEntity<String> handleContactCreation(@RequestBody List<WebhookEvent> events) throws URISyntaxException {
        logger.info("Iniciando metodo handleContactCreation para {} eventos", events.size());
        contactServiceLog.saveContactsLog(events);
        logger.info("Finalizando metodo handleContactCreation ");
        return ResponseEntity.created(new URI("/webhook/contact-creation")).body("Webhook recebido com sucesso!");
    }
}