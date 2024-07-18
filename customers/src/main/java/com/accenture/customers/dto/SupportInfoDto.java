package com.accenture.customers.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Setter @Getter @ConfigurationProperties(prefix = "support")
public class SupportInfoDto {
    private String message;
    private Map<String, String> contactInfo;
    private List<String> phoneNumbers;
}
