package com.example.springexam.model.entity.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TopicType {
  TESTING("Testing"),
  CONTAINER_ORCHESTRATION("Container Orchestration"),
  CLOUD_NATIVE_APPLICATION_DELIVERY("Cloud Native Application Delivery"),
  CLOUD_NATIVE_ARCHITECTURE("Cloud Native Architecture"),
  CLOUD_NATIVE_OBSERVABILITY("Cloud Native Observability"),
  KUBERNETES_FUNDAMENTALS("Kubernetes Fundamentals"),
  SPRING_CORE("Spring Core"),
  SECURITY("Security"),
  SPRING_AOP("Spring AOP"),
  SPRING_MVC("Spring MVC"),
  SPRING_SECURITY("Spring security"),
  SPRING_BOOT("Spring Boot"),
  SPRING_BOOT_ACTUATOR("Spring Boot Actuator"),
  DATA_MANAGEMENT("Data Management"),
  CONFIGURATION("Configuration"),
  SPRING_ACTUATOR("Spring Actuator");

  private final String displayName;

  TopicType(String displayName) {
    this.displayName = displayName;
  }

  public static TopicType fromDisplayName(String displayName) {
    if (displayName == null || displayName.isBlank()) {
      return null;
    }
    String normalizedInput = displayName.trim().toLowerCase();
    return Arrays.stream(values())
            .filter(topic -> topic.displayName.toLowerCase().equals(normalizedInput))
            .findFirst().orElseThrow();
  }
}
