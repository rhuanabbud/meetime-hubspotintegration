package br.com.meetime.hubspotintegration.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contactlog")
public class ContactLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "objectid")
    private Long objectId;

    @Column(name = "propertyname")
    private String propertyName;

    @Column(name = "propertyvalue")
    private String propertyValue;

    @Column(name = "changesource")
    private String changeSource;

    @Column(name = "eventid")
    private Long eventId;

    @Column(name = "subscriptionid")
    private Integer subscriptionId;

    @Column(name = "portalid")
    private Integer portalId;

    @Column(name = "appid")
    private Integer appId;

    @Column(name = "occurredat")
    private Long occurredAt;

    @Column(name = "eventtype")
    private String eventType;

    @Column(name = "attemptnumber")
    private Integer attemptNumber;
}