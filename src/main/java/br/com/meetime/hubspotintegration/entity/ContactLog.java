package br.com.meetime.hubspotintegration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ContactLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long objectId;
    private String propertyName;
    private String propertyValue;
    private String changeSource;
    private Long eventId;
    private Integer subscriptionId;
    private Integer portalId;
    private Integer appId;
    private Long occurredAt;
    private String eventType;
    private Integer attemptNumber;
}