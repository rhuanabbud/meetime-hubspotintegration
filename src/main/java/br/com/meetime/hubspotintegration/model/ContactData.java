package br.com.meetime.hubspotintegration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactData {
    private String firstName;
    private String lastName;
    private String email;
}