package br.com.meetime.hubspotintegration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookEvent {
    private long objectId;
    private String changeSource;
    private long eventId;
    private int subscriptionId;
    private int portalId;
    private int appId;
    private long occurredAt;
    private int attemptNumber;
}