package br.com.meetime.hubspotintegration.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_log")
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