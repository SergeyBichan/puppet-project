package com.aston.frontendpracticeservice.kafka.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaConfigurationProperties {
    private String topic;
    private String groupId;
}
